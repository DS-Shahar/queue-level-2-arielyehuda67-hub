public class main
{
    public static void main(String[] args)
    {
        // ===== ex1 =====
    Queue<Character> qChars = new Queue<Character>();
    qChars.insert('c');
    qChars.insert('c');
    qChars.insert('a');
    qChars.insert('c');

    System.out.println("ex1:");
    printQueue(ex1(qChars));

    // ===== ex2 =====
    Queue<String> qStrings = new Queue<String>();
    qStrings.insert("aa");
    qStrings.insert("bb");
    qStrings.insert("aa");

    System.out.println("ex2:");
    System.out.println(ex2(qStrings));

    // ===== ex3 =====
    Queue<Integer> qNums = new Queue<Integer>();
    qNums.insert(1);
    qNums.insert(2);
    qNums.insert(1);
    qNums.insert(3);

    System.out.println("ex3:");
    printQueue(ex3(qNums));

    // ===== ex4 =====
    Queue<Integer> qToSort = new Queue<Integer>();
    qToSort.insert(4);
    qToSort.insert(1);
    qToSort.insert(3);
    qToSort.insert(2);

    System.out.println("ex4:");
    printQueue(ex4(qToSort));

    // ===== ex5 =====
    Queue<Integer> q1 = new Queue<Integer>();
    q1.insert(1);
    q1.insert(3);
    q1.insert(5);

    Queue<Integer> q2 = new Queue<Integer>();
    q2.insert(2);
    q2.insert(4);
    q2.insert(6);

    System.out.println("ex5:");
    printQueue(ex5(q1, q2));

    // ===== ex6 =====
    Queue<Integer> qEven = new Queue<Integer>();
    qEven.insert(2);
    qEven.insert(4);
    qEven.insert(1);
    qEven.insert(6);
    qEven.insert(8);

    System.out.println("ex6:");
    System.out.println(ex6(qEven));
    }

    public static <T> Queue<T> copyQueue(Queue<T> source)
    {
        Queue<T> result = new Queue<T>();
        Queue<T> backup = new Queue<T>();

        while (!source.isEmpty())
        {
            T value = source.remove();
            result.insert(value);
            backup.insert(value);
        }

        while (!backup.isEmpty())
            source.insert(backup.remove());

        return result;
    }

    public static <T> void printQueue(Queue<T> q)
    {
        Queue<T> temp = copyQueue(q);
        while (!temp.isEmpty())
            System.out.println(temp.remove());
    }

     public static <T> boolean isIn(Queue<T> q, T x)
    {
        Queue<T> backup = new Queue<T>();
        boolean found = false;

        while (!q.isEmpty())
        {
            T value = q.remove();
            if (value.equals(x))
                found = true;
            backup.insert(value);
        }

        while (!backup.isEmpty())
            q.insert(backup.remove());

        return found;
    }

    public static Queue<Integer> ex1(Queue<Character> source)
    {
        Queue<Integer> result = new Queue<Integer>();
        Queue<Character> backup = new Queue<Character>();

        if (source.isEmpty())
            return result;

        char prev = source.remove();
        backup.insert(prev);
        int count = 1;

        while (!source.isEmpty())
        {
            char curr = source.remove();
            backup.insert(curr);

            if (curr == prev)
                count++;
            else
            {
                result.insert(count);
                count = 1;
                prev = curr;
            }
        }

        result.insert(count);

        while (!backup.isEmpty())
            source.insert(backup.remove());

        return result;
    }

    public static boolean ex2(Queue<String> source)
    {
        Queue<String> seen = new Queue<String>();
        Queue<String> backup = new Queue<String>();

        while (!source.isEmpty())
        {
            String x = source.remove();

            if (isIn(seen, x))
            {
                backup.insert(x);
                while (!source.isEmpty())
                    backup.insert(source.remove());
                while (!backup.isEmpty())
                    source.insert(backup.remove());
                return true;
            }

            seen.insert(x);
            backup.insert(x);
        }

        while (!backup.isEmpty())
            source.insert(backup.remove());

        return false;
    }

    public static Queue<Integer> ex3(Queue<Integer> source)
    {
        Queue<Integer> result = new Queue<Integer>();
        Queue<Integer> seen = new Queue<Integer>();
        Queue<Integer> backup = new Queue<Integer>();

        while (!source.isEmpty())
        {
            int x = source.remove();
            backup.insert(x);

            if (!isIn(seen, x))
            {
                seen.insert(x);
                result.insert(x);
            }
        }

        while (!backup.isEmpty())
            source.insert(backup.remove());

        return result;
    }

    public static Queue<Integer> ex4(Queue<Integer> source)
    {
        Queue<Integer> sorted = new Queue<Integer>();
        Queue<Integer> temp = new Queue<Integer>();

        while (!source.isEmpty())
        {
            int min = source.remove();
            temp.insert(min);

            while (!source.isEmpty())
            {
                int x = source.remove();
                if (x < min)
                {
                    temp.insert(min);
                    min = x;
                }
                else
                    temp.insert(x);
            }

            sorted.insert(min);

            while (!temp.isEmpty())
                source.insert(temp.remove());
        }

        return sorted;
    }

    public static Queue<Integer> ex5(Queue<Integer> q1, Queue<Integer> q2)
    {
        Queue<Integer> result = new Queue<Integer>();

        while (!q1.isEmpty() && !q2.isEmpty())
        {
            if (q1.head() <= q2.head())
                result.insert(q1.remove());
            else
                result.insert(q2.remove());
        }

        while (!q1.isEmpty())
            result.insert(q1.remove());

        while (!q2.isEmpty())
            result.insert(q2.remove());

        return result;
    }

    public static int ex6(Queue<Integer> source)
    {
        Queue<Integer> backup = new Queue<Integer>();

        int currLen = 0, currSum = 0;
        int maxLen = 0, maxSum = 0;

        while (!source.isEmpty())
        {
            int x = source.remove();
            backup.insert(x);

            if (x % 2 == 0)
            {
                currLen++;
                currSum += x;

                if (currLen > maxLen)
                {
                    maxLen = currLen;
                    maxSum = currSum;
                }
            }
            else
            {
                currLen = 0;
                currSum = 0;
            }
        }

        while (!backup.isEmpty())
            source.insert(backup.remove());

        return maxSum;
    }

}
