    public static void main(String[] args) 
    {
        BinNode<Integer> root = new BinNode<>(2);
        root.setLeft(new BinNode<>(4));
        root.setRight(new BinNode<>(6));
        root.getLeft().setLeft(new BinNode<>(5));

        System.out.println("--- תוצאות הבדיקה ---");

        // 1. הדפסה
        System.out.print("1. ערכי הצמתים המיוחדים: ");
        printSpecial(root);
        System.out.println();

        // 2. כמות
        int count = countSpecial(root);
        System.out.println("2. מספר הצמתים המיוחדים: " + count);

        // 3. קיום
        boolean exists = existsSpecial(root);
        System.out.println("3. האם קיים לפחות צומת מיוחד אחד? " + exists);

        // 4. האם כולם
        boolean all = allAreSpecial(root);
        System.out.println("4. האם כל הצמתים בעץ מיוחדים? " + all);
    }

    public static boolean isSpecial(BinNode<Integer> node) 
    {
        if (node == null) return false;

        // תנאי 1
        if (node.getValue() % 2 != 0) return false;

        // תנאי 2
        if (node.hasLeft() && node.getLeft().getValue() % 2 != 0) return false;

        // תנאי 3
        if (node.hasRight() && node.getRight().getValue() % 2 != 0) return false;

        return true;
    }

    // 1
    public static void printSpecial(BinNode<Integer> t) 
    {
        if (t != null) 
        {
            if (isSpecial(t)) 
            {
                System.out.print(t.getValue() + " ");
            }
            printSpecial(t.getLeft());
            printSpecial(t.getRight());
        }
    }

    // 2
    public static int countSpecial(BinNode<Integer> t) 
    {
        if (t == null) return 0;
        
        int current = isSpecial(t) ? 1 : 0;
        return current + countSpecial(t.getLeft()) + countSpecial(t.getRight());
    }

    // 3
    public static boolean existsSpecial(BinNode<Integer> t) 
    {
        if (t == null) return false;
        if (isSpecial(t)) return true;
        
        return existsSpecial(t.getLeft()) || existsSpecial(t.getRight());
    }

    // 4
    public static boolean allAreSpecial(BinNode<Integer> t) 
    {
        if (t == null) return true;
        
        if (!isSpecial(t)) return false;
        
        return allAreSpecial(t.getLeft()) && allAreSpecial(t.getRight());
    }
}
