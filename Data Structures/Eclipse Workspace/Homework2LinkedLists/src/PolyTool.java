//class of basic functions for polynomials stored in polyArray
public class PolyTool {

	PolyTool() {
		// no need to do anything, this class takes input from outside performs
		// operations and returns the results of those operations only
	}
	
	
	
	
	
	// finds proper place to insert a new poly term and then calls inputReg
	public SimpleLinkedList inputInOrder(SimpleLinkedList poly, PolyTerm newTerm) {
		Node current = poly.header;
		PolyTerm toInsert = new PolyTerm(newTerm.getCoef(),
				newTerm.getExponent());
		if (current != null) {
			while (current.next != null
					&& ((PolyTerm) current.data).getExponent() > toInsert
							.getExponent()) {
				current = current.next;
			}
			// if the exponents are equal, set coefficient to sum of coefs
			if (((PolyTerm) current.data).getExponent() == toInsert
					.getExponent()) {
				((PolyTerm) current.data).setPolyTerm(
						((PolyTerm) current.data).getCoef()
								+ toInsert.getCoef(),
						((PolyTerm) current.data).getExponent());
			} else if (((PolyTerm) current.data).getExponent() > toInsert
					.getExponent())
				current.next = new Node(toInsert, null);
			else
				inputReg(current, toInsert);
		} else {
			poly.header = new Node(toInsert, null);
		}
		return poly;
	}

	// inputs new poly term at place of current and shifts rest of list over
	public void inputReg(Node current, PolyTerm polyTerm) {
		PolyTerm tempTerm = ((PolyTerm)current.data);
		Node tempPoint = current.next;
		current.data = polyTerm;
		current.next = new Node(tempTerm,tempPoint);
	}

	
	
	
	
	// Adds two polynomials
	public SimpleLinkedList addPoly(SimpleLinkedList l1, SimpleLinkedList l2) {

		SimpleLinkedList sum = new SimpleLinkedList();

		Node current = l1.header;
		while (current != null) {
			inputInOrder(sum, ((PolyTerm) current.data));
			current = current.next;
		}

		current = l2.header;
		while (current != null) {
			inputInOrder(sum, ((PolyTerm) current.data));
			current = current.next;
		}
		cleanUp(sum);
		return sum;
	}

	
	
	
	
	// subtracts polynomials
	public SimpleLinkedList subtractPoly(SimpleLinkedList l1,
			SimpleLinkedList l2) {

		SimpleLinkedList diff = new SimpleLinkedList();

		// adds all terms of l1 positively
		Node current = l1.header;
		while (current != null) {
			inputInOrder(diff, ((PolyTerm) current.data));
			current = current.next;
		}

		// adds all terms of l2 negatively
		current = l2.header;
		while (current != null) {
			inputInOrder(diff,
					new PolyTerm(-((PolyTerm) current.data).getCoef(),
							((PolyTerm) current.data).getExponent()));
			current = current.next;
		}
		cleanUp(diff);
		return diff;
	}

	
	
	
	
	// multiplies two polynomials stores solution in polyArray[index3]
	public SimpleLinkedList multPoly(SimpleLinkedList l1, SimpleLinkedList l2){

		SimpleLinkedList prod = new SimpleLinkedList();

		Node current1 = l1.header;
		while (current1 != null) {
			int exp1 = ((PolyTerm) current1.data).getExponent();
			double coef1 = ((PolyTerm) current1.data).getCoef();
			Node current2 = l2.header;
			while (current2 != null) {
				int exp2 = ((PolyTerm) current2.data).getExponent();
				double coef2 = ((PolyTerm) current2.data).getCoef();
				inputInOrder(prod, new PolyTerm(coef1 * coef2, exp1 + exp2));
				current2 = current2.next;
			}
			current1 = current1.next;
		}
		cleanUp(prod);
		return prod;
	}

	
	
	
	
	//divides two polynomials stores quotient in index3 and remainder in index4
	public SimpleLinkedList[] dividePoly(SimpleLinkedList l1,
			SimpleLinkedList l2) {
		SimpleLinkedList tempL1 = new SimpleLinkedList();
		Node current = l1.header;
		while(current != null){
			double coef = ((PolyTerm) current.data).getCoef();
			int exp = ((PolyTerm) current.data).getExponent();
			inputInOrder(tempL1,new PolyTerm(coef,exp));
			current = current.next;
		}
		SimpleLinkedList[] quotientRem = new SimpleLinkedList[2];
		quotientRem[0] = new SimpleLinkedList();
		quotientRem[1] = new SimpleLinkedList();
		
		Node leadingTerm2 = l2.header;
		Node leadingTerm1 = tempL1.header;
		PolyTerm toBeAdded;
		double coef;
		int exp;
		while (((PolyTerm) leadingTerm1.data).getCoef() !=0 &&
				((PolyTerm) leadingTerm1.data).getExponent() >= 
				((PolyTerm) leadingTerm2.data).getExponent()) {

			coef = ((PolyTerm) leadingTerm1.data).getCoef()
					/ ((PolyTerm) leadingTerm2.data).getCoef();
			exp = ((PolyTerm) leadingTerm1.data).getExponent()
					- ((PolyTerm) leadingTerm2.data).getExponent();

			toBeAdded = new PolyTerm(coef, exp);

			inputInOrder(quotientRem[0], toBeAdded);

			SimpleLinkedList subTerm = new SimpleLinkedList();
			inputInOrder(subTerm, toBeAdded);

			subTerm = multPoly(subTerm, l2);
			tempL1 = subtractPoly(tempL1, subTerm);

			leadingTerm1 = tempL1.header;
		}
		quotientRem[1] = tempL1;
		cleanUp(quotientRem[0]);
		cleanUp(quotientRem[1]);
		return quotientRem;
	}

	
	
	
	
	// evaluates a polynomial at a given double value
	public double evalPoly(SimpleLinkedList poly, double value) {
		double output = 0;
		Node current = poly.header;
		double coef;
		int exp;
		while (current != null) {
			coef = ((PolyTerm) current.data).getCoef();
			exp = ((PolyTerm) current.data).getExponent();
			output += coef * Math.pow(value, ((double) exp));
			current = current.next;
		}
		return output;
	}

	
	
	
	
	// removes terms with 0 coefficient from polynomial
	public void cleanUp(SimpleLinkedList poly) {
		Node current = poly.header;
		while (current != null) {
			if (((PolyTerm) current.data).getCoef() == 0) {
				poly.remove(current.data);
			}
			current = current.next;
		}
		if(poly.header == null){
			inputInOrder(poly, new PolyTerm(0,0));
		}
	}

	
	
	
	
	// prints the polynomial in index position of polyArray
	public void print(SimpleLinkedList poly) {
		Node current = poly.header;
		String output = "";

		while (current != null) {
			output += ((PolyTerm) current.data).toString() + " + ";
			current = current.next;
		}
		if(poly.header != null)
			output = output.substring(0, output.length()-3);

		System.out.println(output);
	}

}
