class StrList {
    String[] contents;
    public StrList() { this.contents = new String[1]; }

    private void expandTo(int size) {
        if(size > this.contents.length) {
            String[] newContents = new String[this.contents.length + 10];
            for(int i = 0; i < this.contents.length; i += 1) {
                newContents[i] = this.contents[i];
            }
            this.contents = newContents;
        }
    }

    public void append(String i) {
        int currentSize = this.contents.length;
        expandTo(currentSize + 1);
        this.contents[currentSize] = i;
    }

    public String get(int i) {
        return this.contents[i];
    }

    public static void main(String[] args) {
        StrList sl = new StrList();
        sl.append("4");
        sl.append("5");
        sl.append("6");
        System.out.println(sl.contents.length);
        System.out.println(sl.get(0));
        System.out.println(sl.get(1));
        System.out.println(sl.get(10));
    }
}
