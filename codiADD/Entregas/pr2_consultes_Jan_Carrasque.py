import sys
from pathlib import Path
from contextlib import closing
from dataclasses import dataclass
from types import SimpleNamespace

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))
from db import connectar


@dataclass
class Llibre:
    id: int
    titol: str
    any_pub: int
    disponibles: int
    autor_id: int


def llibres_de_autor(cn, nom: str) -> list[Llibre]:
    with closing(cn.cursor(dictionary=True)) as cur:
        cur.execute("""
            SELECT l.id, l.titol, l.any_pub, l.disponibles, l.autor_id
            FROM llibre l
            JOIN autor a ON a.id = l.autor_id
            -- Uneixo llibre i autor perquè necessito el nom de l'autor per filtrar els seus llibres.
            WHERE a.nom LIKE %s
        """, (f"%{nom}%",))

        return [Llibre(**fila) for fila in cur.fetchall()]


def socis_amb_retard(cn, dies: int) -> list[SimpleNamespace]:
    with closing(cn.cursor(dictionary=True)) as cur:
        cur.execute("""
            SELECT s.id, s.nom, s.email, l.titol, p.data_prestec,
                   DATEDIFF(CURDATE(), p.data_prestec) AS dies_retard
            FROM prestec p
            JOIN soci s ON s.id = p.soci_id
            -- Uneixo prestec i soci per obtenir les dades del soci que té el préstec.
            JOIN llibre l ON l.id = p.llibre_id
            -- Uneixo prestec i llibre per obtenir el títol del llibre que encara no s'ha retornat.
            WHERE p.data_retorn IS NULL
              AND DATEDIFF(CURDATE(), p.data_prestec) > %s
        """, (dies,))

        return [SimpleNamespace(**fila) for fila in cur.fetchall()]


def top_llibres(cn, n: int) -> list[SimpleNamespace]:
    with closing(cn.cursor(dictionary=True)) as cur:
        cur.execute("""
            SELECT l.id, l.titol, COUNT(p.id) AS total_prestecs
            FROM llibre l
            JOIN prestec p ON p.llibre_id = l.id
            -- Uneixo llibre i prestec per relacionar cada llibre amb els seus préstecs i poder comptar-los.
            GROUP BY l.id, l.titol
            ORDER BY total_prestecs DESC
            LIMIT %s
        """, (n,))

        return [SimpleNamespace(**fila) for fila in cur.fetchall()]


def llibres_esgotats(cn) -> list[SimpleNamespace]:
    with closing(cn.cursor(dictionary=True)) as cur:
        cur.execute("""
            SELECT l.id, l.titol, a.nom AS autor, l.disponibles
            FROM llibre l
            JOIN autor a ON a.id = l.autor_id
            -- Uneixo llibre i autor per obtenir el nom de l'autor de cada llibre esgotat.
            WHERE l.disponibles = 0
        """)

        return [SimpleNamespace(**fila) for fila in cur.fetchall()]


if __name__ == "__main__":

    cn = connectar()

    try:
        print("\n--- LLIBRES D'AUTOR ---")
        for llibre in llibres_de_autor(cn, "Mercè Rodoreda"):
            print(llibre)

        print("\n--- SOCIS AMB RETARD ---")
        for soci in socis_amb_retard(cn, 15):
            print(soci)

        print("\n--- TOP LLIBRES ---")
        for llibre in top_llibres(cn, 3):
            print(llibre)

        print("\n--- LLIBRES ESGOTATS ---")
        for llibre in llibres_esgotats(cn):
            print(llibre)

    finally:
        cn.close()