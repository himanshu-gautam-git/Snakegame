import java.util.Scanner;
import java.lang.*;
class Main{
    	static int row,col,score=0;
	 char board[][]=new char[50][50];
     char check='l';
     		Node head;
     class Node
	{
		int x,y;
        char value;
		Node next;
		Node(int x , int y)
		{
            value='@';
            this.x=x;
            this.y=y;
			next=null;
        }
    }
	public void createBoard(int r , int c)
	{
		row=r;
		col=c;
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(i==0||j==0||i==row-1||j==col-1)
				{
					board[i][j]='$';
				}
				else
				{
					board[i][j]=' ';
				}
			}
		}
	}
	public void print()
	{
        System.out.println("SCORE: "+score);
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	public void snakeBody()
	{
        int x1=5,y1=15;
        int c=4;
        while(c!=0)
        {
        Node n=new Node(x1,y1);
            if(head==null)
            {
                head=n;
            }
            else
            {
                Node n1=head;
                while(n1.next!=null)
                {
                    n1=n1.next;
                }
                n.value='*';
                n1.next=n;
            }
            x1=x1;
            y1=y1+1;
            c--;
        }
    }
    public void createFood()
    {
        Node food;
        int x3=(int)(Math.random() * (row-2));
        int y3=(int)(Math.random() * (col-2));
        Node n3=new Node(x3,y3);
        food=n3;
        food.value='o';
        board[food.x][food.y]=food.value;
    }
    public void movement()
    {
        char ch;
        int x3=0,y3=0;
        char left='l',right='r',up='u',down='d';
        Scanner s1=new Scanner(System.in);
        while(true)
        {
        ch=s1.next().charAt(0);
        if(ch==left)
        {
            if(check==right)
            {
                continue;
            }
            else
            {
                check=left;
            x3=head.x;
            y3=(head.y)-1;
            }
        }
        else if(ch==right)
        {
            if(check==left)
            {
                continue;
            }
            else{
                check=right;
            x3=head.x;
            y3=(head.y)+1;
            }
        }
        else if(ch==down)
        {
            if(check==up)
            {
                continue;
            }
            else{
                check=down;
            x3=(head.x)+1;
            y3=head.y;
            }
        }
        else if(ch==up)
        {
            if(check==down)
            {
                continue;
            }
            else{
                check=up;
            x3=(head.x)-1;
            y3=head.y;
            }
        }
        moveBody(x3,y3);
        }
    }
    public void moveBody(int x3 , int y3)
    {

        if(x3==0 &&y3==0)
        {
            return ;
        }
        if(x3==0)
        {
            x3=row-2;
        }
         else if(x3==row-1)
        {
            x3=1;
        }
        if(y3==0)
        {
            y3=col-2;
        }
        else if(y3==col-1)
        { 
            y3=1;
        }
        if(board[x3][y3]=='*')
        {
            collision();
        }
        if(board[x3][y3]=='o')
        {
            addNode();
            createFood();
            score++;
        }
        moveSnakeBody(x3,y3);
        System.out.print("\033[H\033[2J");  
System.out.flush();  
        print();
    }
    public void addNode()
    {
     Node n0=head;
     while(n0.next!=null)
     {
         n0=n0.next;
     }   
     Node n01=new Node((n0.x),(n0.y)+1);
     n01.value='*';
     n0.next=n01;
    }
    public void moveSnakeBody(int x3 , int y3)
    {
        Node n4=new Node(x3,y3);
        head.value='*';
        n4.next=head;
        head=n4;
        board[n4.x][n4.y]=n4.value;
        Node n6=head;
        Node n7=null;
        while(n6!=null)
        {
            if(n6.next==null)
            {
                n7.next=n6.next;
                break;
            }
            n7=n6;
            n6=n6.next;
        }
        board[n6.x][n6.y]=' ';
        Node  n5=head;
        while(n5!=null)
        {
            board[n5.x][n5.y]=n5.value;
            n5=n5.next;
        }
    }
    public void collision()
    {  
                System.out.print("\033[H\033[2J");  
System.out.flush();
        char str[]={'G','A','M','E',' ','O','V','E','R'};
    int j=0,i=0;
    for(i=20;i<=28;i++){
        board[8][i]=str[j++];
    }
    char str2[]={'S','C','O','R','E',' '};
    j=0;
    for(i=20;i<=25;i++)
    {
        board[10][i]=str2[j++];
    }
    for(i=0;i<row;i++)
    {
        for(j=0;j<col;j++)
        {
    if(board[i][j]=='$' || board[i][j]>='A' && board[i][j]<='Z' || board[i][j]>='a' && board[i][j]<='z'&& board[i][j]!='o')
    {
            System.out.print(board[i][j]);
    }
    else
    {
        System.out.print(" ");
    }
        }
                System.out.println();
    }
                    System.exit(0);
    }
	public static void main(String[] args) 
	{
		Scanner s=new Scanner(System.in);
        Main m=new Main();
        System.out.println("Enter the size of board you want to display");
        System.out.println("ROWS");
		row=s.nextInt();
        System.out.println("COL");
		col=s.nextInt();
        System.out.println("To Move Your Snake LEFT Press 'l' Followed By ENTER");
        System.out.println("To Move Your Snake RIGHT Press 'r' Followed By ENTER");
        System.out.println("To Move Your Snake UP Press 'u' Followed By ENTER");
        System.out.println("To Move Your Snake DOWN Press 'd' Followed By ENTER");
		m.createBoard(row,col);
		m.snakeBody();
        m.createFood();
        m.movement();
	}   
}
