import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


class Node implements Comparable<Node>{
    public int value;
    public int height;
    public Node left;
    public Node right;

    public Node(){
        this.value = 0;
        this.height = 0;
        this.left = null;
        this.right = null;
    }

    public Node(int value){
        this.value = value;
    }

    public Node(int value, int height){
        this.value = value;
        this.height = height;
    }

    public Node(int value ,int height,Node left,Node right){
        this.value = value;
        this.height = height;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }

    @Override
    public int compareTo(Node value2){
        return Integer.compare(this.value, value2.value);
    }
}

class Avl{
    private class Helper{
        private int getHeight(Node node){
            if (node == null)
                return -1;
            return node.height;
        }

        private int getBalanceFactor(Node node){
            if (node == null)
                return 0;
            return getHeight(node.left) - getHeight(node.right);
        }

        private Node previousNode(Node node){
            Node ptr = node;
            while (ptr.right != null)
                ptr = ptr.right;
            return ptr;
        }
    
        private Node rotateRight(Node node){
            Node leftNode = node.left;
            Node rightNode = leftNode.right;
            leftNode.right = node;
            node.left = rightNode;
            node.height =  Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            leftNode.height =  Math.max(getHeight(leftNode.left), getHeight(leftNode.right)) + 1;
            return leftNode;
        }

        private Node rotateLeft(Node node){
            Node rightNode = node.right;
            Node leftNode = rightNode.left;
            rightNode.left = node;
            node.right = leftNode;
            node.height =  Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            rightNode.height =  Math.max(getHeight(rightNode.left), getHeight(rightNode.right)) + 1;
            return rightNode;

        }

        private Node balanceTree(Node node){
            node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
            int balanceFactor = getBalanceFactor(node);

            if (balanceFactor == 2){
                if (getHeight(node.left.left) > getHeight(node.left.right)) {
                    node = rotateRight(node);
                } else {
                    node.left = rotateLeft(node.left);
                    node = rotateRight(node);
                }
            }
            
            else if (balanceFactor == -2){
                if (getHeight(node.right.right) > getHeight(node.right.left)){
                    node = rotateLeft(node);
                } 
                else {
                    node.right = rotateRight(node.right);
                    node = rotateLeft(node);
                }
            } 

            return node;
        }
    
      
    }


    private Node root;
    private Helper helper;

    public void initialize(){
        root = null;
        helper = new Helper();
    }

    private Node insert(Node node, int value){
        //for null node initialise a new node 
        if (node == null)
            node =  new Node(value);

        // If smaller value move on left ; if greater value move to right 
        if (value < node.value)
            node.left = insert(node.left, value);
        else if (value > node.value)
            node.right = insert(node.right, value);

        //Rebalance tree as insertion can change balane factor 
        return helper.balanceTree(node);
    }
    public void  insert (int value){
        root = insert(root, value);
    }

            
    private Node delete(Node node, int value){
        //if node is null delete nothing 
        if (node == null)
            return null;

        //Find the value of node by moving to left or right as it satisfies BST
        if (value < node.value)
            node.left = delete(node.left, value);
        else if (value > node.value)
            node.right = delete(node.right, value);
        else {
            // if node has 1 or no children
            if (node.left == null || node.right == null){
                if(node.left == null){
                    node = node.right;
                }
                else{
                    node = node.left;
                }
            } 
            //if node has 2 children 
            else {
                Node predecessor = helper.previousNode(node.left);
                node.value = predecessor.value;
                node.left = delete(node.left, predecessor.value);
            }
        }
        //Rebalance tree as deleting node can change balance factor
        if (node != null){
            node = helper.balanceTree(node);
        }
        return node;
    }
    public void delete(int value){
        root = delete(root, value);
    }

    private void preOrderTraversal(Node node, int value1, int value2, List<Node> list){
        if (node == null)
            return;

        //check if value lies in range
        if (node.value >= value1 && node.value <= value2)
            list.add(node);

        //go to left or right depening on value 
        if (node.value > value1)
            preOrderTraversal(node.left, value1, value2, list);

        if (node.value < value2)
            preOrderTraversal(node.right, value1, value2, list);
    }
    public Node search(int value){
        //Move the ptr till last node 
        //if value is greater than current move to right else to left 
        //and if value == current  then return ptr  
        Node ptr = root;
        while (ptr != null){
            if (value > ptr.value)
                ptr = ptr.right;
            else if (value < ptr.value)
                ptr = ptr.left;
            else
                return ptr;
        }
        return null;
    }
    public List<Node> search(int value1, int value2){
        List<Node> list = new ArrayList<>();
        preOrderTraversal(root, value1, value2, list);
        Collections.sort(list);
        return list;
    }


}

class PerformOperations{
    public String parseInput(String input){
        int indexOfOpeningBracket = input.indexOf('(');
        int indexOfClosingBracket = input.indexOf(')');
        String value = input.substring(indexOfOpeningBracket+1,indexOfClosingBracket);
        return value;
    }
    public List<String> performInput(String[] args){
        Avl avl = new Avl();
        List<String> output = new ArrayList<>();
        List<String> input = new ArrayList<>();

        try{
            File file = new File(args[0]);
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                input.add(line);
            }

            for(String line: input){
                if (line.contains("Initialize")){
                    avl.initialize();
                } 
                else if (line.contains("Insert")){
                    String value = parseInput(line);
                    avl.insert(Integer.valueOf(value));
                } 
                else if (line.contains("Delete")){
                    String value = parseInput(line);
                    avl.delete(Integer.valueOf(value));
                } 
                else if (line.contains("Search")){
                    String value = parseInput(line);
                    String[] values = value.split(",");
                    if (values.length == 1){
                        int searchValue = Integer.valueOf(values[0]);
                        if (avl.search(searchValue) == null)
                            output.add("NULL");
                        else
                            output.add(avl.search(searchValue).toString());
                    } 
                    else {
                        int searchValue1 = Integer.valueOf(values[0]);
                        int searchValue2 = Integer.valueOf(values[1]);
                        if ((avl.search(searchValue1,searchValue2).isEmpty()))
                            output.add("NULL");
                        else {
                            List<Node> searchList =  avl.search(searchValue1, searchValue2);
                            StringBuilder sb = new StringBuilder();
                            for (Node searcher : searchList){
                                sb.append(searcher.value);
                                sb.append(",");
                            }
                            sb.deleteCharAt(sb.length() - 1);
                            output.add(sb.toString());
                        }
    
                    }
    
                }
            
            }

        
            sc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }


        return output;
    }

    public void performOutput(List<String> output){

        try{
            FileWriter fw = new FileWriter("output_file.txt");
            for (String str : output){
                fw.write(str + System.lineSeparator());
            }
            fw.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}

public class avltree{
    public static void main(String[] args) {
        PerformOperations performOperation = new PerformOperations();
        List<String> output  = performOperation.performInput(args);
        performOperation.performOutput(output);

    }
}

