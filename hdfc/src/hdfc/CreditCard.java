package hdfc;

public class CreditCard {

	public static void main(String[] args) {
		System.out.println("hello");
		isPrime(10);
		linear();
		binary();
		insertionSort();
	}


public static void print() {
	System.out.println("inside print");
	int arr[] = {1,2,3,4,5,6};
	for(int i=0;i<arr.length;i++) {
		System.out.println(arr[i]);
	}
	int i=0;
	i=i+2;
	i=i-1;
	i=i*2;
	i=i/3;
	i=i%2;
	}

public static void add(int i , int j) {
	int result = i+j;
	System.out.println(result);
	for(int k=0;k<10;k++) {
		System.out.println(k);
	}
}
public static void fibbo(int a,int b) {
	System.out.println("fibbo");
	System.out.println(0);
	System.out.println(1);
	while(a<15) {
	int c=a+b;
	a=b;
	b=c;
	System.out.println(c);
	}
}
public static void isPrime(int number) {
    if (number <= 1) {
        System.out.println("not a prime number");
        return;
    }

    for (int i = 2; i <= Math.sqrt(number); i++) {
        if (number % i == 0) {
            System.out.println("not a prime number");
            return;
        }
    }

    System.out.println("prime number");
}

public static void linear() {
    int[] arr = {2, 6, 7, 17, 29, 31, 67, 89,101};
    int search = 8;
    boolean found = false;

    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == search) {
            System.out.println(search + " found at index " + i);
            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println(search + " not found in array");
    }
}

public static void binary() {
    int[] arr = {2, 6, 7, 17, 29, 31, 67, 89, 101};
    int search = 8;
    int low = 0, high = arr.length - 1;
    boolean found = false;

    while (low <= high) {
        int mid = (low + high) / 2;

        if (arr[mid] == search) {
            System.out.println(search + " found at index " + mid);
            found = true;
            break;
        } else if (arr[mid] < search) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    if (!found) {
        System.out.println(search + " not found in array");
    }
}

public static void insertionSort() {
int arr[] = {1002,110,23445,6789,9807,6455,75432};
    for (int i = 1; i < arr.length; i++) {
        int key = arr[i];
        int j = i - 1;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
    for (int num : arr) {
        System.out.print(num + " ");
    }
}
}