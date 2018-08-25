//===================================================================================================================================================================
//
//
//
//
//===================================================================================================================================================================
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class IntegerMultiplication {
	 List<Integer> product(List<Integer> x, List<Integer> y, int b) {
		  // Return z = x * y.  Numbers are stored using base b.
	          // The "digits" are stored in the list with the least
	          // significant digit first.  For example, if b = 10, then
	          // the number 709 will be stored as 9 -> 0 -> 7.
	          // Assume that b is small enough that you will not get any
	     	// overflow of numbers during the operation.
		 List<Integer> z=new ArrayList<>();
		 if(x.size()==1&&y.size()==1) {
				 if(x.get(0)*y.get(0)<b) {
					 z.add(0,x.get(0)*y.get(0));
				 }
				 else {
					 z.add(0,x.get(0)*y.get(0)/b);
					 z.add(0,x.get(0)*y.get(0)%b);
				 }
			 return z;
		 }
		 else
		 {
			 int maxSize=Math.max(x.size(),y.size());

			 List<Integer> xh=new ArrayList<>();
			 List<Integer> xl=new ArrayList<>();
			 List<Integer> yh=new ArrayList<>();
			 List<Integer> yl=new ArrayList<>();
			 int j=maxSize/2;
			 for(int i=0;i<j;i++) {
				 xl.add(x.get(i));
				 yl.add(y.get(i));
			 }
			 for(int i=j;i<x.size();i++) {
				 xh.add(x.get(i));
				 yh.add(y.get(i));
			 }
			 System.out.println("xh="+xh);
			 System.out.println("xl="+xl);
			 System.out.println("yh="+yh);
			 System.out.println("yl="+yl);
			 List<Integer> a=product(xh,yh,b);
			 System.out.println("a="+a);
			 List<Integer> d=product(xl,yl,b);
			 System.out.println("d="+d);
			 List<Integer> k=add(xh,xl,b);
			 System.out.println("k="+k);
			 List<Integer> l=add(yh,yl,b);
			 System.out.println("l="+l);
			 List<Integer> e=product(k,l,b);
			 System.out.println("e="+e);
			 e=subtract(e,a,b);
			 e=subtract(e,d,b);
			 System.out.println("e1="+e);
			 z= add(multiply(a,2*j),multiply(e,j),b);
			 System.out.println("z="+z);
			 z= add(z,d,b);
			 System.out.println("z1="+z);
			 return z;
		 }
		}
	 
	 private List<Integer> multiply(List<Integer> a, int n) {
		for(int i=0;i<n;i++)
			a.add(0, new Integer(0));
		return a;
	}

	public static List<Integer> add(List<Integer> x, List<Integer> y, int b) {
			int i = 0;
			int j = 0;
			int current1 = 0, current2 = 0, res = 0, carry = 0, reminder = 0, modulo = 0;
			List<Integer> z=new ArrayList<>();
			while (i < x.size() && j < y.size()) {
				current1 = x.get(i);
				current2 = y.get(j);
				res = current1 + current2 + carry;
				carry = 0;
				reminder = (int) (res / b);
				modulo = res % b;
				z.add(modulo);
				carry = reminder;
				i++;
				j++;
			}
			while (i < x.size()) {
				res = x.get(i) + carry;
				if (res < b) {
					z.add(res);
					carry = 0;
				} else {
					reminder = (int) (res / b);
					modulo = res % b;
					z.add(modulo);
					carry = reminder;
				}
				i++;
			}
			while (j < y.size()) {
				res = y.get(j) + carry;
				if (res < b) {
					z.add(res);
					carry = 0;
				} else {
					reminder = (int) (res / b);
					modulo = res % b;
					z.add(modulo);
					carry = reminder;
				}
				j++;
			}
			if (carry > 0) {
				z.add(carry);
			}
			return z;
		}

		// Return z = x - y. Numbers are stored using base b.
		public static List<Integer> subtract(List<Integer> x, List<Integer> y,int b) {
			int i = 0;
			int j = 0;
			int current1 = 0, current2 = 0, res = 0, carry = 0, remain = 0;
			List<Integer> z=new ArrayList<>();
			while (i < x.size() && j < y.size()) {
				current1 = x.get(i);
				if(carry == 1) {
					current1 = current1 - 1;
					carry = 0;
				}
				current2 = y.get(j);
				if(current1 < current2) {
					current1 = current1 + b;
					carry = 1;
				}
				res = current1 - current2;
				z.add(res);
				i++;
				j++;
			}
			while (i < x.size()) {
				remain = x.get(i) - carry;
				carry = 0;
				if (remain < 0) {
					remain = remain + b;
					carry = 1;
				}
				if (i + 1 != x.size() || (i + 1 == x.size() && remain != 0))
					z.add(remain);
				i++;
			}
			while (j < y.size()) {
				remain = y.get(j) - carry;
				carry = 0;
				if (remain < 0) {
					remain = remain + b;
					carry = 1;
				}
				if (j + 1 != y.size() && (j + 1 == y.size() && remain != 0))
					z.add(remain);
				j++;
			}
			return z;
		}
		public static void printlist(List<Integer> list) {
			for(int i=list.size()-1;i>=0;i--)
				System.out.print(list.get(i)+" ");
		}
	public static void main(String[] args) {
		List<Integer> a=new ArrayList<>();
		List<Integer> b=new ArrayList<>();
		List<Integer> result=new ArrayList<>();
		int base;
		Scanner in=new Scanner(System.in);
		base=in.nextInt();
		in.nextLine();
		System.out.println("Enter the First integer");
		String str=in.nextLine();
		String[] number=str.trim().split("\\s+");
		for(int i=0;i<number.length;i++)
			a.add(0,Integer.parseInt(number[i]));
		System.out.println("Enter the Second integer");
		str=in.nextLine();
		number=str.trim().split("\\s+");
		for(int i=0;i<number.length;i++)
			b.add(0,Integer.parseInt(number[i]));
		IntegerMultiplication ob=new IntegerMultiplication();
		result=ob.product(a,b,base);
		printlist(result);
}
}
