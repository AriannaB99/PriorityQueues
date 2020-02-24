public class Site {

    //a site can be either open or blocked
    //false = blocked, true = open
    public boolean status;
    public int id;

    public Site() {
        status = false;
        id = 0;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder();
        //if the site is open, print a white square
        if (this.status) {
            sb.append("\u25A2");
        }//if the site is closed, print a black square
        else sb.append("\u25A0");
        sb.append(" ");

        return sb.toString();
    }

}
