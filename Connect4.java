import java.util.Scanner;

public class Connect4 {
    private static final int SOROK = 6; // Tábla sorainak száma
    private static final int OSZLOPOK = 7; // Tábla oszlopainak száma
    private static final char URES = '.'; // Üres hely jelölése
    private static final char JATEKOS_1 = 'X'; // Játékos 1 jele
    private static final char JATEKOS_2 = 'O'; // Játékos 2 jele

    public static void main(String[] args) {
        char[][] tabla = new char[SOROK][OSZLOPOK];
        inicializalTabla(tabla);
        kirajzolTabla(tabla);

        char aktualisJatekos = JATEKOS_1;
        boolean jatekVege = false;

        Scanner scanner = new Scanner(System.in);

        while (!jatekVege) {
            System.out.println("Játékos " + aktualisJatekos + ", te jössz!");
            System.out.print("Adj meg egy oszlopot (0-" + (OSZLOPOK - 1) + "): ");
            int oszlop = scanner.nextInt();

            if (oszlop < 0 || oszlop >= OSZLOPOK) {
                System.out.println("Érvénytelen oszlop! Próbáld újra.");
                continue;
            }

            if (!lepes(tabla, aktualisJatekos, oszlop)) {
                System.out.println("Az oszlop tele van! Próbáld újra.");
                continue;
            }

            kirajzolTabla(tabla);

            if (ellenorizGyozelem(tabla, aktualisJatekos)) {
                System.out.println("Gratulálunk, Játékos " + aktualisJatekos + " nyert!");
                jatekVege = true;
            } else if (tablaTele(tabla)) {
                System.out.println("Döntetlen! A tábla megtelt.");
                jatekVege = true;
            } else {
                aktualisJatekos = (aktualisJatekos == JATEKOS_1) ? JATEKOS_2 : JATEKOS_1;
            }
        }

        scanner.close();
    }

    private static void inicializalTabla(char[][] tabla) {
        for (int sor = 0; sor < SOROK; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK; oszlop++) {
                tabla[sor][oszlop] = URES;
            }
        }
    }

    private static void kirajzolTabla(char[][] tabla) {
        for (int sor = 0; sor < SOROK; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK; oszlop++) {
                System.out.print(tabla[sor][oszlop] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean lepes(char[][] tabla, char jatekos, int oszlop) {
        for (int sor = SOROK - 1; sor >= 0; sor--) {
            if (tabla[sor][oszlop] == URES) {
                tabla[sor][oszlop] = jatekos;
                return true;
            }
        }
        return false;
    }

    private static boolean ellenorizGyozelem(char[][] tabla, char jatekos) {
        // Sorok ellenőrzése
        for (int sor = 0; sor < SOROK; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK - 3; oszlop++) {
                if (tabla[sor][oszlop] == jatekos && tabla[sor][oszlop + 1] == jatekos &&
                    tabla[sor][oszlop + 2] == jatekos && tabla[sor][oszlop + 3] == jatekos) {
                    return true;
                }
            }
        }

        // Oszlopok ellenőrzése
        for (int oszlop = 0; oszlop < OSZLOPOK; oszlop++) {
            for (int sor = 0; sor < SOROK - 3; sor++) {
                if (tabla[sor][oszlop] == jatekos && tabla[sor + 1][oszlop] == jatekos &&
                    tabla[sor + 2][oszlop] == jatekos && tabla[sor + 3][oszlop] == jatekos) {
                    return true;
                }
            }
        }

        // Átlók ellenőrzése (bal-fentről jobb-le)
        for (int sor = 0; sor < SOROK - 3; sor++) {
            for (int oszlop = 0; oszlop < OSZLOPOK - 3; oszlop++) {
                if (tabla[sor][oszlop] == jatekos && tabla[sor + 1][oszlop + 1] == jatekos &&
                    tabla[sor + 2][oszlop + 2] == jatekos && tabla[sor + 3][oszlop + 3] == jatekos) {
                    return true;
                }
            }
        }

        // Átlók ellenőrzése (jobb-fentről bal-le)
        for (int sor = 0; sor < SOROK - 3; sor++) {
            for (int oszlop = 3; oszlop < OSZLOPOK; oszlop++) {
                if (tabla[sor][oszlop] == jatekos && tabla[sor + 1][oszlop - 1] == jatekos &&
                    tabla[sor + 2][oszlop - 2] == jatekos && tabla[sor + 3][oszlop - 3] == jatekos) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean tablaTele(char[][] tabla) {
        for (int oszlop = 0; oszlop < OSZLOPOK; oszlop++) {
            if (tabla[0][oszlop] == URES) {
                return false;
            }
        }
        return true;
    }
}
