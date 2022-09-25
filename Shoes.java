
public class Shoes extends Product
{

    private String size;
    private String colour;



    int[][] shoeStock = new int[2][5];



    public Shoes(String name, String id, double price, int stock, String size, String colour)
    {

        super(name,id,price,0,Category.SHOES);
        this.size = size;
        this.colour = colour;

        if(colour.equalsIgnoreCase("Brown"))
        {
            shoeStock[0][Integer.parseInt(size) - 6] = stock;
        }
        if(colour.equalsIgnoreCase("Black"))
        {
            shoeStock[1][Integer.parseInt(size) - 6] = stock;
        }

    }
    @Override
    public boolean validOptions(String productOptions)
    {
        if(productOptions.equals(""))
        {
            return false;
        }
        String[] opts = productOptions.split(" ");
        if(opts[0].equalsIgnoreCase("Brown") ||opts[0].equalsIgnoreCase("Black") )
        {
            return Integer.parseInt(opts[1]) >= 6 && Integer.parseInt(opts[1]) <= 10;

        }
        return false;


    }
    @Override
    public int getStockCount(String productOptions)
    {
        String[] opts = productOptions.split(" ");
        if(opts[0].equalsIgnoreCase("Brown"))
        {
            return shoeStock[0][Integer.parseInt(opts[1]) - 6];
        }
        if(opts[0].equalsIgnoreCase("Black"))
        {
            return shoeStock[1][Integer.parseInt(opts[1]) - 6];
        }
        return 0;
    }

    public void setStockCount(int stockCount, String productOptions)
    {
        String[] opts = productOptions.split(" ");
        if(opts[0].equalsIgnoreCase("Brown"))
        {
            shoeStock[0][Integer.parseInt(opts[1]) - 6] = stockCount;
        }
        if(opts[0].equalsIgnoreCase("Black"))
        {
            shoeStock[1][Integer.parseInt(opts[1]) - 6] = stockCount;
        }

    }


    public void reduceStockCount(String productOptions)
    {
        String[] opts = productOptions.split(" ");
        if(opts[0].equalsIgnoreCase("Brown"))
        {
            shoeStock[0][Integer.parseInt(opts[1]) - 6]--;
        }
        if(opts[0].equalsIgnoreCase("Black"))
        {
            shoeStock[1][Integer.parseInt(opts[1]) - 6]--;
        }


        // Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
        // Use the variables paperbackStock and hardcoverStock at the top.
        // For "EBook", set the inherited stockCount variable.
    }

     //Print product information in super class and append Book specific information title and author

    public void print()
    {
        // Replace the line below.
        // Make use of the super class print() method and append the title and author info. See the video
        super.print();
    }
}
