package atd_linked_stack;

public class ListElement {
    private char[] name = new char[20]; //до 20 символов
    private char[] address = new char[50]; //до 50 символов

    public ListElement(char[] name, char[] address) {
        if(name.length>20 || address.length>50)
            throw new IllegalArgumentException();
        for (int i = 0; i <name.length ; i++) {
            this.name[i] = name[i];
        }
        for (int i = 0; i < address.length; i++) {
            this.address[i] = address[i];
        }
    }

    public char[] getName() {
        return name;
    }

    public void setName(char[] name) {
        this.name = name;
    }

    public char[] getAddress() {
        return address;
    }

    public void setAddress(char[] address) {
        this.address = address;
    }

    public String addressToString() {
        String str="";
        for (int i=0; i < address.length;i++) {
                str += address[i];
        }
        return str;
    }

    public String nameToString(){
        String str="";
        for (int i=0; i < name.length;i++) {
            str += name[i];
        }
        return str;
    }



    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass() && this != null) {
            ListElement that = (ListElement)obj;

            for (int i = 0; i < this.name.length; i++) {
                if (this.name[i] != ((ListElement) obj).name[i])
                    return false;
            }

            for (int i = 0; i < this.address.length; i++) {
                if (this.address[i] != ((ListElement) obj).address[i])
                    return false;
            }
            return true;
            //return Arrays.equals(this.name, that.name) && Arrays.equals(this.address, that.address);
        } else {
            return false;
        }
    }

    public void printElements(){
        System.out.println( "Address: " + addressToString() + "Name: " + nameToString() );
    }
}
