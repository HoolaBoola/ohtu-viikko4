
package ohtu.intjoukkosovellus;


public class IntJoukko {

    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujoukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;

    public IntJoukko() {
        this(5, 5);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, 5);
    }


    public IntJoukko(int kapasiteetti, int kasvatuskoko) {


        this.kasvatuskoko = 5;
        if (kasvatuskoko > 0) {
            this.kasvatuskoko = kasvatuskoko;
        }

        if (kapasiteetti < 0) {
            kapasiteetti = 5;
        }

        lukujoukko = new int[kapasiteetti];
        this.alkioidenLkm = 0;

    }

    public boolean lisaa(int luku) {

        if (!kuuluu(luku)) {
            lukujoukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == lukujoukko.length) {
                int[] taulukkoOld = lukujoukko;
                lukujoukko = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, lukujoukko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {

        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                return true;
            }
        }

        return false;

    }

    public boolean poista(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                kohta = i;
                lukujoukko[kohta] = 0;
                break;
            }
        }
        if (kohta < 0) {
            return false;
        }

        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            lukujoukko[j] = lukujoukko[j + 1];
        }
        alkioidenLkm--;
        return true;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {

        if (alkioidenLkm == 0) {
            return "{}";
        }

        if (alkioidenLkm == 1) {
            return "{" + lukujoukko[0] + "}";
        }

        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += lukujoukko[i];
            tuotos += ", ";
        }
        tuotos += lukujoukko[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
        
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujoukko[i];
        }
        return taulu;
    }


    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
