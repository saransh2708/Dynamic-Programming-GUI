package sample;
import java.util.*;
import java.lang.*;
import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;

import java.nio.channels.ScatteringByteChannel;

public class Controller {
    @FXML
    private Button button1;
    @FXML
    private Label label1,label2,label3,klabel1,klabel2,klabel3,plabel1,plabel2,plabel3,knlabel1,knlabel2,tlabel1,tlabel2,tlabel3;

    @FXML
    private TextField text1,text2,text3,ktext1,ktext2,ktext3,ptext1,ptext2,ptext3,kntext1,kntext2,ttext1,ttext2,ttext3;
    @FXML
    private TextArea text4;
    @FXML
    private Tab tab1,tab2,tab3,tab4,tab5,tab6;
    public int vert=0,edge=0,bags=0,weight=0;
    public int i=0,sour,cost;
    private int graph[][],val[],w[];
    private int completed[];
    int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 1; v <= vert; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
    void printSolution(int dist[])
    {
        text4.setText("Vertex \t\t Distance from Source\n");
        for (int i = 1; i <= vert; i++)
        {

            text4.appendText((i + " \t\t\t\t " + dist[i]).toString());
        text4.appendText("\n");}
    }

    void dijkstra(int graph[][], int src)
    {
        int dist[] = new int[vert+1];
        Boolean sptSet[] = new Boolean[vert+1];

        for (int i = 1; i <= vert; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 1; count <= vert - 1; count++) {

            int u = minDistance(dist, sptSet);

            sptSet[u] = true;


            for (int v = 1; v <= vert; v++)


                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        printSolution(dist);
    }

    public void whenClicked() {
        try {
            if (i == 0) {
                String s1 = text1.getText();String s2 = text2.getText();
                vert = Integer.parseInt(s1);edge = Integer.parseInt(s2);graph=new int[vert+1][vert+1];
                for (int r = 1; r <= vert; r++) {
                    for (int j = 1; j <= vert; j++) {
                        graph[r][j] = 0;
                    }
               }
                text1.clear();text2.clear();
                label1.setText("Enter Source                      : ");label2.setText("Enter Destination             : ");
                label3.setVisible(true);text3.setVisible(true);tab2.setDisable(true);tab3.setDisable(true);tab4.setDisable(true);tab5.setDisable(true);tab6.setDisable(true);
            }
            else if (i == (edge+1)) {
                String str;
                str = text2.getText();
                sour = Integer.parseInt(str);
                text2.clear();
                tab1.setDisable(true);tab6.setDisable(false);
                dijkstra(graph,sour);
            }
            else if (i > 0) {
                String val1, val2, val3;
                val1 = text1.getText();
                text1.clear();
                val2 = text2.getText();
                text2.clear();
                val3 = text3.getText();
                text3.clear();
                int src, dest, w;
                src = Integer.parseInt(val1);
                dest = Integer.parseInt(val2);
                w = Integer.parseInt(val3);
                graph[src][dest] = w;
                graph[dest][src] = w;

                if (i == edge) {
                    label2.setText("Enter the source  : ");
                    label1.setVisible(false);
                    label3.setVisible(false);
                    text1.setVisible(false);
                    text3.setVisible(false);
                }
            }

            i++;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    int minKey(int key[], Boolean mstSet[])
    {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 1; v <=vert; v++)
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        return min_index;
    }

    void printMST(int parent[], int graph[][])
    {
        text4.setText("  Edge\t\t\tWeight\n");
        for (int i = 2; i <= vert; i++)
        {
            text4.appendText((parent[i] + " \t-\t " + i+"\t\t\t\t"+graph[i][parent[i]]).toString());
            text4.appendText("\n");}
    }

    void primMST(int graph[][])
    {
        int parent[] = new int[vert+1];

        int key[] = new int[vert+1];

        Boolean mstSet[] = new Boolean[vert+1];

        for (int i =1; i <= vert; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[1] = 0;
        parent[1] = -1;

        for (int c = 0; c <vert-1; c++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 1; v <= vert; v++)

                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        printMST(parent, graph);
    }

    public void whenClicked2() {
        try {
            if(i==0)
            {
               String s1,s2;
                s1=ktext1.getText();s2=ktext2.getText();
                vert=Integer.parseInt(s1);edge=Integer.parseInt(s2);
                tab1.setDisable(true);tab3.setDisable(true);tab4.setDisable(true);tab5.setDisable(true);tab6.setDisable(true);
                klabel1.setText("Enter Source                      : ");
                klabel2.setText("Enter Destination             : ");
                graph=new int[vert+1][vert+1];
                for (int r = 0; r < vert; r++) {
                    for (int j = 0; j <vert; j++) {
                        graph[r][j] = 0;
                    }
                }
                ktext1.clear();ktext2.clear();klabel3.setVisible(true);ktext3.setVisible(true);
            }
            else if(i>0)
            {
                String val1, val2, val3;
                val1 = ktext1.getText();ktext1.clear();val2 = ktext2.getText();ktext2.clear();val3 = ktext3.getText();ktext3.clear();
                int src, dest, w;
                src = Integer.parseInt(val1);dest = Integer.parseInt(val2);w = Integer.parseInt(val3);
                graph[src][dest] = w;graph[dest][src] = w;
            }

            if(i==edge)
            {
                tab6.setDisable(false);tab2.setDisable(true);
                primMST(graph);
            }

            i++;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void whenClicked3() {
        try {
            if(i==0)
            {
                String s1,s2;
                s1=ptext1.getText();
                s2=ptext2.getText();
                vert=Integer.parseInt(s1);
                edge=Integer.parseInt(s2);
                tab1.setDisable(true);tab2.setDisable(true);tab4.setDisable(true);tab5.setDisable(true);tab6.setDisable(true);
                plabel1.setText("Enter Source                      : ");plabel2.setText("Enter Destination             : ");
                graph=new int[vert+1][vert+1];
                for (int r = 1; r <= vert; r++) {
                    for (int j = 1; j <= vert; j++) {
                        graph[r][j] = 0;
                    }
                }
                ptext1.clear();ptext2.clear();

                plabel3.setVisible(true);ptext3.setVisible(true);
            }
            else if(i>0)
            {
                String val1, val2, val3;
                val1 = ptext1.getText();ptext1.clear();
                val2 = ptext2.getText();ptext2.clear();
                val3 = ptext3.getText();ptext3.clear();
                int src, dest, w;
                src = Integer.parseInt(val1);dest = Integer.parseInt(val2);w = Integer.parseInt(val3);
                graph[src][dest] = w;graph[dest][src] = w;
            }
             if(i==edge)
            {
                tab6.setDisable(false);tab3.setDisable(true);
                primMST(graph);
            }

            i++;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    int max(int a, int b)
    {
        return (a > b) ? a : b;
    }

     void printknapSack(int W, int wt[],
                              int val[], int n)
    {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] +
                            K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        int res = K[n][W];
        text4.setText("Maximum price He can get :  " + Integer.toString(res));
        text4.appendText("\nBags Selected of Weight : \n");
        w = W;
        for (i = n; i > 0 && res > 0; i--) {

            if (res == K[i - 1][w])
                continue;
            else {

                text4.appendText(wt[i - 1] + " \n");

                res = res - val[i - 1];
                w = w - wt[i - 1];
            }
        }
    }

    public void whenClicked4()
    {
        try {
            if (i == 0) {
                knlabel1.setText("Enter weight   : ");
                knlabel2.setText("Enter price   : ");
                String s1, s2;
                s1 = kntext1.getText();
                s2 = kntext2.getText();
                bags = Integer.parseInt(s1);weight = Integer.parseInt(s2);
                kntext1.clear();kntext2.clear();
                val = new int[bags];
                w = new int[bags];
                tab1.setDisable(true);tab2.setDisable(true);tab3.setDisable(true);tab5.setDisable(true);tab6.setDisable(true);
            }

             else if(i>0) {
                String s1, s2;
                s1 = kntext1.getText();kntext1.clear();
                s2 = kntext2.getText();kntext2.clear();
                int v1, w1;
                v1 = Integer.parseInt(s2);
                w1 = Integer.parseInt(s1);
                val[i - 1] = v1;
                w[i - 1] = w1;
            }
            if (i == bags ) {
                tab6.setDisable(false);
                tab4.setDisable(true);
                printknapSack(weight, w, val, bags);
            }
            i++;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    int least(int c)
    {
        int i,nc=999;
        int min=999,kmin=999;

        for(i=0;i <vert;i++)
        {
            if((graph[c][i]!=0)&&(completed[i]==0))
                if(graph[c][i]+graph[i][c] < min)
                {
                    min=graph[i][0]+graph[c][i];
                    kmin=graph[c][i];
                    nc=i;
                }
        }

        if(min!=999)
            cost+=kmin;

        return nc;
    }

    void mincost(int city)
    {
        int i,ncity;

        completed[city]=1;

        text4.appendText(city+1+"--->");
        ncity=least(city);

        if(ncity==999)
        {
            ncity=0;
            text4.appendText(Integer.toString(ncity+1));
            cost+=graph[city][ncity];

            return;
        }

        mincost(ncity);
    }
    public void whenClicked5()
    {
        try {
            if (i == 0) {
                String s1, s2;
                s1 = ttext1.getText();
                s2 = ttext2.getText();
                vert = Integer.parseInt(s1);
                edge = Integer.parseInt(s2);
                tab1.setDisable(true);tab2.setDisable(true);tab3.setDisable(true);tab4.setDisable(true);tab6.setDisable(true);
                tlabel1.setText("Enter Source                   : ");
                tlabel2.setText("Enter Destination         : ");
                graph = new int[vert ][vert ];
                completed=new int[vert];
                for (int r = 0; r < vert; r++) {
                    for (int j = 0; j < vert; j++) {
                        graph[r][j] = 0;
                    }
                }
                ttext1.clear();ttext2.clear();
                tlabel3.setVisible(true);ttext3.setVisible(true);
            } else if (i > 0) {
                String val1, val2, val3;
                val1 = ttext1.getText();
                ttext1.clear();
                val2 = ttext2.getText();
                ttext2.clear();
                val3 = ttext3.getText();
                ttext3.clear();
                int src, dest, w;
                src = Integer.parseInt(val1);
                dest = Integer.parseInt(val2);
                w = Integer.parseInt(val3);
                graph[src-1][dest-1] = w;
                graph[dest-1][src-1] = w;
            }
            if (i == edge) {
                tab6.setDisable(false);
                tab5.setDisable(true);
                text4.setText("\n\nThe Path is:\n");
                mincost(0);
                text4.appendText("\n\nMinimum cost is "+cost);

            }
            i++;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void whenClicked6()
    {
        i=0;
        tab1.setDisable(false);tab2.setDisable(false);tab3.setDisable(false);tab4.setDisable(false);tab5.setDisable(false);
    }


}


