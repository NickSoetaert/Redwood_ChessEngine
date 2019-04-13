enum BitBoardEnum{
    WP(""), WN("N"), WB("B"), WR("R"), WQ("Q"), WK("K"),
    BP(""), BN("N"), BB("B"), BR("R"), BQ("Q"), BK("K");

    private String UCI;

    private BitBoardEnum(String UCI)
    {
        this.UCI = UCI;
    }
}