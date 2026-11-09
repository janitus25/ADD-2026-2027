import sys
from pathlib import Path

import mysql.connector

sys.path.append(str(Path(__file__).resolve().parent.parent))

from db import connectar


def alta_soci(cn, nom, email):
    """Insereix un soci i retorna el seu id nou, o None si l'email ja existeix."""
    cur = cn.cursor()
    try:
        cur.execute("INSERT INTO soci (nom, email) VALUES (%s, %s)", (nom, email))
        cn.commit()
        return cur.lastrowid
    except mysql.connector.IntegrityError as e:
        cn.rollback()
        if e.errno == 1062:
            print("Error 1062: l'email ja existeix, no s'ha creat el soci.")
            return None
        raise


def canviar_nom(cn, id, nom):
    """Canvia el nom d'un soci. Retorna True si s'ha trobat, False si no."""
    cur = cn.cursor()
    cur.execute("UPDATE soci SET nom = %s WHERE id = %s", (nom, id))
    cn.commit()
    return cur.rowcount == 1


def baixa_soci(cn, id):
    """Esborra un soci. Retorna False si no existeix o si té préstecs (errno 1451)."""
    cur = cn.cursor()
    try:
        cur.execute("DELETE FROM soci WHERE id = %s", (id,))
        cn.commit()
        return cur.rowcount == 1
    except mysql.connector.IntegrityError as e:
        cn.rollback()
        if e.errno == 1451:
            print("Error 1451: el soci té préstecs associats, no es pot esborrar.")
            return False
        raise


def main():
    nom = input("Nom del soci nou: ")
    email = input("Email del soci nou: ")
    nou_nom = input("Nom nou per al canvi: ")
    id_prestecs = int(input("Id d'un soci amb préstecs: "))
    id_inexistent = int(input("Id que no existeixi: "))

    cn = connectar()

    nou = alta_soci(cn, nom, email)
    print("ALTA      ->", nou)
    print("DUPLICAT  ->", alta_soci(cn, nom, email))
    print("CANVI NOM ->", canviar_nom(cn, nou, nou_nom))
    print("CANVI NOM ->", canviar_nom(cn, id_inexistent, nou_nom))
    print("BAIXA     ->", baixa_soci(cn, id_inexistent))
    print("BAIXA     ->", baixa_soci(cn, id_prestecs))
    print("BAIXA     ->", baixa_soci(cn, nou))   # deixa la BD com estava

    cn.close()


if __name__ == "__main__":
    main()