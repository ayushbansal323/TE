class SendMoreMoney
{
    public static void main (String [] args)
    {
        int s,e,n,d,m,o,r,y,send,more,money;
         
        for (s = 1; s < 10; s++)
        {
            for (e = 0; e < 10; e++)
            {
                for (n = 0; n < 10; n++)
                {
                    for (d = 0; d < 10; d++)
                    {
                        for (m = 1; m < 10; m++)
                        {
                            for (o = 0; o < 10; o++)
                            {
                                for (r = 0; r < 10; r++)
                                {
                                    for (y = 0; y < 10; y++)
                                    {
                                        send = (s*1000) + (e*100) + (n*10) + (d*1);
                                        more = (m*1000) + (o*100) + (r*10) + (e*1);
                                        money = (m*10000) + (o*1000) + (n*100) + (e*10) + (y*1);
                                         
                                        if (s != e && s != n && s != d && s != m && s != o && s != r && s != y)
                                            if (e != n && e != d && e != m && e != o && e != r && e != y)
                                                if (n != d && n != m && n != o && n != r && n != y)
                                                    if (d != m && d != o && d != r && d != y)
                                                        if (m != o && m != r && m != y)
                                                            if (o != r && o != y)
                                                                if (send + more == money)
                                                                {
                                                                    System.out.println(" SEND     " + s + e + n +d);
                                                                    System.out.println(" MORE     " + m + o + r + e);
                                                                    System.out.println("-----    ----- ");
                                                                    System.out.println("MONEY    " + m + o + n + e + y);
                                                                    System.out.println();
                                                                }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
