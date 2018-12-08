package ee461l.stockapp;

public class CryptoInfo {

    private Crypto[] crypto;
    private String[] cryptoSymbols;

    public void buildCryptoSymbols(){
        cryptoSymbols = new String[crypto.length];
        for(int i = 0; i < crypto.length; i++){
            cryptoSymbols[i] = crypto[i].getSymbol();
        }
    }

    public Crypto[] getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto[] crypto) {
        this.crypto = crypto;
    }

    public String[] getCryptoSymbols() {
        return cryptoSymbols;
    }

    public void setCryptoSymbols(String[] cryptoSymbols) {
        this.cryptoSymbols = cryptoSymbols;
    }
}
