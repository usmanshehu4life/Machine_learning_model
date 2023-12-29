import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Assignment3{

public static class tree{
   public Node root;
   
   public class Node{
       double radius_mean,texture_mean,perimeter_mean,area_mean,smoothness_mean,compactness_mean,concavity_mean,concavepoints_mean,symmetry_mean,fractal_dimension_mean;
       Node left,right;
       int index;
       String diagnosis;
       String ID;
       int level;

       //This is the Node constructor that's used inside the tree constructor to make a Node,
       //it has 10 attributes radius -->fractal_dimension
       //it also has an ID, the diagnosis Letter and an index to suggest exactly where it is on the excel spreadsheet 
       //The level represents at which level the Node is currently placed on, root is always level 2, the next layer is level 3 and then 4 etc.. it starts from
       //2 because the first attribute is placed on the 3rd column in the excel sheet and from index-0 pov it's index is 2
        public Node(String[][]p,int index){
         this.radius_mean=Double.parseDouble(p[index][2]);
         this.texture_mean=Double.parseDouble(p[index][3]);
         this.perimeter_mean=Double.parseDouble(p[index][4]);
         this.area_mean=Double.parseDouble(p[index][5]);
         this.smoothness_mean=Double.parseDouble(p[index][6]);
         this.compactness_mean=Double.parseDouble(p[index][7]);
         this.concavity_mean=Double.parseDouble(p[index][8]);
         this.concavepoints_mean=Double.parseDouble(p[index][9]);
         this.symmetry_mean=Double.parseDouble(p[index][10]);
         this.fractal_dimension_mean=Double.parseDouble(p[index][11]);
         this.ID=p[index][0];
         this.diagnosis=p[index][1];
         this.index=index;
        }
        
      }


      //This is the tree Constructor, it takes the entire excel spreadsheet as the parameter
      //The index represents the row of the excel spreadsheet
      // NOTE: The excel spreadsheet is index-1 based while the arrays are always 0 based therefore the index here= excel rownumber-1
      public tree(String[][]p,int index){
         this.root=new Node(p, index);
         this.root.level=2;         
      }
   

      //This is a function that inserts a node into the tree based on an index
      //The index represent the row from the excel sheet
      //p represents the excel file 2D array
   public void insert(String[][]p,int index){
      int currentlevel=this.root.level;
      Node nodetobeinserted=new Node(p,index);
      Node i=this.root;
      Node j=i;
      int k=0;    
while(i !=null){
      if(i.level==2){if(i.radius_mean>nodetobeinserted.radius_mean){j=i;i=i.left;currentlevel++;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==3){if(i.texture_mean>nodetobeinserted.texture_mean){j=i;i=i.left;currentlevel++;;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==4){if(i.perimeter_mean>nodetobeinserted.perimeter_mean){j=i;i=i.left;currentlevel++;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==5){if(i.area_mean>nodetobeinserted.area_mean){j=i;i=i.left;currentlevel++;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==6){if(i.smoothness_mean>nodetobeinserted.smoothness_mean){j=i;i=i.left;currentlevel++;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==7){if(i.compactness_mean>nodetobeinserted.compactness_mean){j=i;i=i.left;currentlevel++;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==8){if(i.concavity_mean>nodetobeinserted.concavity_mean){j=i;i=i.left;currentlevel++;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==9){if(i.concavepoints_mean>nodetobeinserted.concavepoints_mean){j=i;i=i.left;currentlevel++;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==10){if(i.symmetry_mean>nodetobeinserted.symmetry_mean){j=i;i=i.left;currentlevel++;k=0;}else{j=i;i=i.right;currentlevel++;k=1;}}else{
      if(i.level==11){if(i.radius_mean>nodetobeinserted.fractal_dimension_mean){j=i;i=i.left;currentlevel=2;k=0;}else{j=i;i=i.right;currentlevel=2;k=1;}}}}}}}}}}}
   }

   if(k==0){j.left=nodetobeinserted;}else{
      if(k==1){j.right=nodetobeinserted;}
   }

if(currentlevel !=11){
nodetobeinserted.level=currentlevel+1;}else{nodetobeinserted.level=2;}


}

















//This function is used to find the K nearest neighbours
//P represents the excel sheet
//Index represents the row number of the excel
//K represents the number of neighbours that's being searched
//This function returns a list of K items, it contains the required indexes 1-->570 that represent the nearest neighbours from the excel file
//it's used in the vote function to accumulate the vote results 
public ArrayList<Integer> findK_neighbours(String[][]p,int index,int K){
      Node nodetobeinserted=new Node(p,index);
      Node i=this.root;
      Node j=i;
      int direction=0;
      // double currentdistance;
      // int currentindex;
      ArrayList<Double> alldistances=new ArrayList<>();
      ArrayList<Integer> allindexes=new ArrayList<>();
while(i !=null){if(i.level==2){if(i.radius_mean>nodetobeinserted.radius_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==3){if(i.texture_mean>nodetobeinserted.texture_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==4){if(i.perimeter_mean>nodetobeinserted.perimeter_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==5){if(i.area_mean>nodetobeinserted.area_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==6){if(i.smoothness_mean>nodetobeinserted.smoothness_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==7){if(i.compactness_mean>nodetobeinserted.compactness_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==8){if(i.concavity_mean>nodetobeinserted.concavity_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==9){if(i.concavepoints_mean>nodetobeinserted.concavepoints_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==10){if(i.symmetry_mean>nodetobeinserted.symmetry_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}else{
      if(i.level==11){if(i.radius_mean>nodetobeinserted.fractal_dimension_mean){j=i;i=i.left;direction=0;}else{j=i;i=i.right;direction=1;}}}}}}}}}}}
   allindexes.add(j.index);
   alldistances.add(euclidean(nodetobeinserted.index,j.index, p));
      }
   if(allindexes.size()<K){
      if(direction==0){i=j.right;}else{if(direction==1){i=j.left;}}
      j=i;
      while(i !=null){if(i.level==2){if(i.radius_mean>nodetobeinserted.radius_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==3){if(i.texture_mean>nodetobeinserted.texture_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==4){if(i.perimeter_mean>nodetobeinserted.perimeter_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==5){if(i.area_mean>nodetobeinserted.area_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==6){if(i.smoothness_mean>nodetobeinserted.smoothness_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==7){if(i.compactness_mean>nodetobeinserted.compactness_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==8){if(i.concavity_mean>nodetobeinserted.concavity_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==9){if(i.concavepoints_mean>nodetobeinserted.concavepoints_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==10){if(i.symmetry_mean>nodetobeinserted.symmetry_mean){j=i;i=i.left;}else{j=i;i=i.right;}}else{
      if(i.level==11){if(i.radius_mean>nodetobeinserted.fractal_dimension_mean){j=i;i=i.left;}else{j=i;i=i.right;}}}}}}}}}}}   
   allindexes.add(j.index);
   alldistances.add(euclidean(nodetobeinserted.index,j.index, p));
   
   }
}
   ArrayList<Integer> minimumindexes=new ArrayList<>();
   while(minimumindexes.size()<K && alldistances.size()!=0 ){
      double minimum=alldistances.get(0);
      int i1=0;
   for (int l = 0; l < alldistances.size(); l++) {
      if(alldistances.get(l)<minimum){minimum=alldistances.get(l);i1=l;}
   }
   minimumindexes.add(allindexes.get(i1));
   alldistances.remove(i1);
   allindexes.remove(i1);
}    
   return minimumindexes;
}


//This function compares the results of 2 elements from the same patient list and accumulates the total number of B and M votes
//It returns B if the number of B's is greater and M if the number of M's is greater
//neareset neighbours is a list of containing all the nearest neighbours that was made from the findK_neighbours() function
public String VoteResult(String[][]p,ArrayList<Integer>nearestneighbours){
   int total_b=0;
   int total_m=0;
   for (int i = 0; i < nearestneighbours.size(); i++) {
      
      if((p[nearestneighbours.get(i)][1]).equals("B")){total_b++;}
       if((p[nearestneighbours.get(i)][1]).equals("M")){total_m++;}
   }
   if(total_b>total_m){return "B";}else{return "M";}
}


// This function is to compare the two conditions :the real one and the one generated from the VoteResult() returns true if they're the same and false if not
public boolean compareresults(String vote,String real){
   if(vote.equals(real)){return true;}else{return false;}
}


// This function calculates the euclidean distance to find the nearest neighbours
// Euclidean distance = Sqrt (sum of (the differences of all the 10 attributes of 2 points)^2)
   public double euclidean(int i,int j,String[][]p){
      //i index from point 1
      //j index from point 2
      double answer=0;
      double sum=0;
      for (int k = 2; k < 12; k++) {
         sum=(Double.parseDouble(p[j][k])-Double.parseDouble(p[i][k]));
         sum=sum*sum;
         answer=answer+sum;
      }
      answer=Math.sqrt(answer);
      return answer;
   }
}

 
//ALL the Generic Functions are inserted here
public interface KNN {

 public static int[] sortDoubleArray(String[][] array2D, int columnIndex) {
        int n = array2D.length;
        double[] doubleArray = new double[n];
        
        // Extract values from the specified column and convert them to double
        for (int i = 0; i < n; i++) {
            doubleArray[i] = Double.parseDouble(array2D[i][columnIndex]);
        }
        
        // Sort the double array
        Arrays.sort(doubleArray);
        
        // Convert the sorted double values back to the previous row index of the 2D array
        for (int i = 0; i < n; i++) {
            array2D[i][columnIndex] = String.valueOf(doubleArray[i]);
        }
        
        // Create the resulting integer array based on the row index of the 2D array
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = Integer.parseInt(array2D[i][0]);
        }
        
        return result;
    }


   //This function was made at the start of the project to print the info of a said index from the excel sheet
   //it prints every information in the excel sheet from the ID column-->fractal_dimension


   public static void patientinfo(String[][]p,int i){
      System.out.println("[");
      for (int j = 0; j < 12; j++) {
      if(j==11){System.out.println(p[i][j]);}else{   
      System.out.printf(p[i][j]+",");
      }
      }
      System.out.println("]");
   }


   //This is part of the merge sort function
   private static double[] merge(double[]array1,double[]array2){
        double[]combined=new double[array1.length+array2.length];
        int index=0;
        int i=0;
        int j=0;

        while(i<array1.length && j<array2.length){
            if(array1[i]<array2[j]){
                combined[index]=array1[i];
                index++;
                i++;
            }else{
                combined[index]=array2[j];
                index++;
                j++;
            }
        }
        while(i<array1.length){combined[index]=array1[i];index++;i++;}
        while(j<array2.length){combined[index]=array2[j];index++;j++;}
        return combined;
    } 


    //this is the mergesort function 
    public static double[] mergesort(double[]array){
        if(array.length==1){return array;}
        int midIndex=(array.length)/2;
        double[]left=mergesort(Arrays.copyOfRange(array, 0, midIndex));
        double[]right=mergesort(Arrays.copyOfRange(array, midIndex, array.length));

        return merge(left,right); 
    }

//This is a basic random number generator that returns a number between 1 and 569
//It doesn't return 0 because row 0 in the excel file are the headers


   public static int randomnumbergenerator(){
      Random number=new Random();
      return number.nextInt(1,569);
   }
   
//A simple search algorithm
//it's used in the list generator function to avoid the list generating 2 of the same numbers/ to ensure that the list produces unique numbers between 1-570
    public static boolean brutesearch(int[]arr,int target){
      for (int i = 0; i < arr.length; i++) {
         if(arr[i]==target){return true;}
      }
      return false;
    }


    //Produces a list of random numbers based on the integer count and then returns the list as a fixed size array
   public static int[] listgenerator(int count){
      int[]log=new int[count];
      int temp=randomnumbergenerator();    
      for (int i = 0; i < count; i++) {
         while(brutesearch(log, temp)==true){temp=randomnumbergenerator();}
         log[i]=temp;
      }
      return log;
   }
   public static String[][] finallist(int[]arr,String[][]p){
      String[][]l=new String[arr.length][12];

      for (int i = 0; i < l.length; i++) {
      for (int j = 0; j < 12; j++){
         l[i][j]=(p[arr[i]][j]);}
      }
      return l;
   }
}

   


public static void main(String[] args) {
   
 String path="C:\\Users\\pierh\\OneDrive\\Desktop\\Java projects\\Assignment 3\\data.csv";       
 String line="";
 String[][]patient=new String[570][12];
 //-------------------------------------------------------------------------------------------------------------
 //------------This section is dedicated to extract the numbers from the excel file-----------------------------
 //-------------------------------------------------------------------------------------------------------------
try {
     BufferedReader br=new BufferedReader(new FileReader(path));
     int j=0;
     while((line=br.readLine())!=null){
      String[]values=line.split(",");
      for (int i = 0; i < 12; i++) {
         patient[j][i]=values[i];
   }
      j++;
}
} 
catch (FileNotFoundException e) {e.printStackTrace();}
catch(IOException e){e.printStackTrace();}
//--------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------
Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer: \n");
        System.out.println("1 for N=50\n2 for N=150\n3 for N=250\n4 for N=350\n5 for N=450");
        int number = scanner.nextInt();
      int Training=0;
        System.out.println("You entered: " + number);
         if(number==1){Training=50;}
         if(number==2){Training=150;}
         if(number==3){Training=250;}
         if(number==4){Training=350;}
         if(number==5){Training=450;}
        scanner.close();
         

 // This is the number N required in the assignment, this number produces the right amount of test and training patients

System.out.println("\nHere we're Testing for N ="+Training+"\n");
int Testing=Training/4;
int quotient=Training%4;
if(quotient>0){Testing++;}
int total=Training+Testing;
   int []mylist=new int[total];
   mylist=KNN.listgenerator(mylist.length);
System.out.println("Unique Training List: ");
   System.out.print("[");
   for (int i = 0; i < mylist.length-Testing; i++) {
      System.out.print( mylist[i]+",");
   }
   System.out.print("]\n\n");

System.out.println("\nUnique Testing List: ");
   System.out.print("[");
   for (int i = mylist.length-Testing; i < mylist.length; i++) {
      System.out.print( mylist[i]+",");
   }
   System.out.print("]\n\n");   



   String[][]myfinallist=new String[mylist.length][12];

   myfinallist=KNN.finallist(mylist, patient);

  
   
//--------------------------------------------------------------------------------------------------------------------------------------------
// Here the sorted radius takes the testing elements sorts it by the radius and then takes the median and selects it as the root of the three
double[] sortedradius=new double[Training];

for (int i = 0; i < sortedradius.length; i++) {
   sortedradius[i]=Double.parseDouble(myfinallist[i][2]);
   
}
sortedradius=KNN.mergesort(sortedradius);

double median=sortedradius[sortedradius.length/2];
int indexbeingsearched=0;
for (int i = 0; i < sortedradius.length; i++) {
   if(median==(Double.parseDouble(myfinallist[i][2]))){indexbeingsearched=i;}
}


tree mytree=new tree(patient,mylist[indexbeingsearched]);

System.out.println("the index of your root from Training list is: "+mytree.root.index);
// System.out.println("the radius mean of my tree's root is: "+mytree.root.radius_mean);
for (int i = 0; i < sortedradius.length; i++) {
   // System.out.println("my list ["+i+"]= "+mylist[i]);
   if(mylist[i] !=mytree.root.index){
      // System.out.println("Now the index ["+(i)+"] is being inserted");
      mytree.insert(patient, mylist[i]);
      }
   }
   
//------------------------------------------------------------------------------------------------------------------------------------------
//this is the list for the training elements, it contains all the unique indexes generated from the list generator   
int[] elementstobetested=new int[mylist.length-sortedradius.length];

for (int i = 0; i < elementstobetested.length; i++) {
   elementstobetested[i]=mylist[sortedradius.length+i];
   
}

//------------------------------------------------------------------------------------------------------------------------------------------
//This is an arraylist to hold the nearest distances produced by the findK_neighbours
//We make it equal the list generated by the findK_neighbours() to then perform the vote
//Once the vote is performed the K_neighbours list generated from the next training index is generated and inserted here and then the vote is performed again
ArrayList<Integer> nearestdistances=new ArrayList<>();
int accuracy1=0;//Accuracy1 is the total number of votes generated from K=3
int accuracy2=0;//Accuracy2 is the total number of votes generated from K=5
int accuracy3=0;//Accuracy3 is the total number of votes generated from K=7

//------------------------------------------------------------------------------------------
long starttime3=System.nanoTime();
for (int j = 0; j < elementstobetested.length; j++) {
nearestdistances=mytree.findK_neighbours(patient,elementstobetested[j]+1,7);


if((mytree.VoteResult(patient,nearestdistances)).equals(patient[elementstobetested[j]+1][1])){accuracy3++;}
}
long endtime3=System.nanoTime();
//-------------------------------------------------------------------------------------------
long starttime2=System.nanoTime();
for (int j = 0; j < elementstobetested.length; j++) {
nearestdistances=mytree.findK_neighbours(patient,elementstobetested[j]+1,5);

if((mytree.VoteResult(patient,nearestdistances)).equals(patient[elementstobetested[j]+1][1])){accuracy2++;}
}
long endtime2=System.nanoTime();
//-------------------------------------------------------------------------------------------
long starttime1=System.nanoTime();
for (int j = 0; j < elementstobetested.length; j++) {
nearestdistances=mytree.findK_neighbours(patient,elementstobetested[j]+1,3);


if((mytree.VoteResult(patient,nearestdistances)).equals(patient[elementstobetested[j]+1][1])){accuracy1++;}
}
long endtime1=System.nanoTime();




System.out.println("your time with K=3 is "+(endtime1-starttime1)+" ns!");
System.out.println("your time with K=5 is "+(endtime2-starttime2)+" ns!");
System.out.println("your time with K=7 is "+(endtime3-starttime3)+" ns!");



//Returns the total number of elements that were accurate from all the 3 different cases of K neighbours; 3 5 and 7
System.out.println("\nyour accuracy with K=3 is "+accuracy1+"/"+elementstobetested.length);
System.out.println("your accuracy with K=5 is "+accuracy2+"/"+elementstobetested.length);
System.out.println("your accuracy with K=7 is "+accuracy3+"/"+elementstobetested.length);

}
}