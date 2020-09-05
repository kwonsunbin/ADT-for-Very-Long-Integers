// LongInt ADT for unbounded integers

public class LongInt {
	public char[] storage;
	public boolean sign = true ;
	public int numLength ;
	public String asString;
	
	

	public LongInt(String s) {
	
		if (s.indexOf("-") == 0) {
			s = s.substring(1);
			this.sign = false;
		}
		this.asString = s;
		this.numLength = s.length();
		storage = new char[s.length()];
		for (int i=0; i < s.length(); i++) {
			this.storage[i] = (s.charAt(i));
		}
	}
  
	public int checkOver(int a, int b, int c) {
		int ans = 0;
		if (a+b+c>=10) {
			ans = 1;
		}
		return ans;
	}
  
  public String partSum(int a, int b, int c) {
	  int ans = a+b+c ;
	  if (a+b+c>=10) {
		  ans = a+b+c-10;
	  }
	  return Integer.toString(ans);
  }
  
  public int checkUnder(int a, int b, int c) {
	  int ans = 0;
	  if (a < b+c) {
		  ans = 1;
	  }
	  return ans;
  }
  public String partSubtract(int a, int b, int c) {
	  int ans ;
	  if (a< b+c) {
		  ans = (10+a-b-c);
	  }else {ans =a-b-c;}
	  return Integer.toString(ans);
  }
  
  
  

  public int checkSign(boolean first, boolean second) {
	  int flag = 0 ;
	  
	  if((first ==true && second == true)) {
		  flag = 1; 
	  }else if((first ==false && second== false)) {
		  flag = 2; 
	  }else if((first == true) && (second==false)) {
		  flag =  3;
	  }else if((first == false) && (second==true)) {
		  flag = 4; 
	  }
	return flag;
  }
  

  public int compareAbs(LongInt first, LongInt second) {
	  int flag = 0 ;
	  if(first.numLength > second.numLength) {
		  flag = 1;     
	  }else if(first.numLength< second.numLength) {
		  flag = 2;    
	  }else if(first.numLength == second.numLength) {
		  for (int i=0; i<first.numLength;i++) {
			  if(first.storage[i]-'0'>second.storage[i]-'0') {
				  flag =1;         
				  break;
			  }else if(first.storage[i]-'0'<second.storage[i]-'0') {
				  flag =2;        
				  break;
			  }
			  if( (i==first.numLength-1) && (first.storage[i]-'0'== second.storage[i]-'0')) {
				  flag =3; 
			  }
		  }
	  }
	  return flag;  
  }
  
  public String arrAddForMul(String[] strArr) {
	  
	  String resultArr;
	  String temp = "0";
	  
	  for (int i =0; i<strArr.length; i++) {
		  temp = absAddForMul(temp,strArr[i]);
	  }
	  resultArr = temp;
	  
	  return resultArr;
  }
  
  public String absAddForMul(String first, String second) {
		String result = "";
		 
		if(first.length() > second.length()) {
			int flag = 0;
			for(int i=first.length()-1; i>=0; i--) {
				if (i >= first.length()-second.length()) {
					result = partSum(first.charAt(i)-'0', second.charAt(i-(first.length() - second.length()))-'0', flag)+result;
					flag =  checkOver(first.charAt(i)-'0', second.charAt(i-(first.length() - second.length()))-'0', flag);
				}else{
					result = partSum(first.charAt(i)-'0', 0, flag)+result;
					flag =  checkOver(first.charAt(i)-'0', 0, flag);
				}
			}
			if (flag==1) {
				  result = "1"+result;
			}

		}else if(first.length() < second.length()) {
			int flag = 0;
			for(int i=second.length()-1; i>=0; i--) {
				if (i >= second.length()-first.length()) {
					result = partSum(second.charAt(i)-'0', first.charAt(i-(second.length()-first.length()))-'0', flag)+result;
					flag =  checkOver(second.charAt(i)-'0', first.charAt(i-(second.length()-first.length()))-'0', flag);
				}else {
					result = partSum(second.charAt(i)-'0', 0, flag)+result;
					flag =  checkOver(second.charAt(i)-'0', 0, flag);
				}
			}
			if (flag==1) {
				  result = "1"+result;
			}
		}else {
			int flag = 0;

			
			for(int i=first.length()-1; i>=0; i--) {
				result = partSum(first.charAt(i)-'0', second.charAt(i)-'0', flag) + result;
				flag =  checkOver(first.charAt(i)-'0', second.charAt(i)-'0', flag);
			}
			if (flag==1) {
				result = "1"+result;
			}
		}

		return result;
	}
  
  public String repeatAddForMul(String target,int howMany) {
	  if (howMany == 0) {
			return "0";
		} 
	  String repeatSum = target;
	  for (int n=0; n<howMany-1; n++) {
		  repeatSum = absAddForMul(repeatSum,target);
	  }
	  return repeatSum;
  }

  public String absAdd(LongInt first, LongInt second) {
	  String result = "";
	  int flag = 0;
	  
	  if(first.numLength > second.numLength) {
		  for(int i=first.numLength-1; i>=0; i--) {
			  if (i >= first.numLength-second.numLength) {
				  result = partSum(first.storage[i]-'0', second.storage[i-(first.numLength-second.numLength)]-'0', flag)+result;
				  flag =  checkOver(first.storage[i]-'0', second.storage[i-(first.numLength-second.numLength)]-'0', flag);
			  }else{
				  result = partSum(first.storage[i]-'0', 0, flag)+result;
				  flag =  checkOver(first.storage[i]-'0', 0, flag);
			  }
		  }
		  if (flag==1) {
			  result = "1"+result;
		  }

	  }else if(first.numLength < second.numLength) {
		  for(int i=second.numLength-1; i>=0; i--) {
			  if (i >= second.numLength-first.numLength) {
				  result = partSum(second.storage[i]-'0', first.storage[i-(second.numLength-first.numLength)]-'0', flag)+result;
				  flag =  checkOver(second.storage[i]-'0', first.storage[i-(second.numLength-first.numLength)]-'0', flag);
			  }else {
				  result = partSum(second.storage[i]-'0', 0, flag)+result;
				  flag =  checkOver(second.storage[i]-'0', 0, flag);
			  }
		  }
		  if (flag==1) {
			  result = "1"+result;
		  }
		  
	  }else if(first.numLength == second.numLength){
		  for(int i=first.numLength-1; i>=0; i--) {
			  result = partSum(first.storage[i]-'0', second.storage[i]-'0', flag)+result;
			  flag =  checkOver(first.storage[i]-'0', second.storage[i]-'0', flag);
		  }
		  if (flag==1) {
			  result = "1"+result;
		  }
	  }
	  
	  return result;
  }
  
  public String absSubtract(LongInt first, LongInt second) { 
	  String result = "";
	  int flag = 0;
	  int compareFlag = compareAbs(first,second);
	  
	  if (compareFlag ==1) { 
		  
		  for(int i=first.numLength-1; i>=0; i--) {
			  if (i < first.numLength - second.numLength) {
				  if (flag ==1 ) {
					  result = partSubtract(first.storage[i]-'0', 0, flag)+result;
					  flag =  checkUnder(first.storage[i]-'0', 0, flag);
				  }else {
					  result = partSubtract(first.storage[i]-'0', 0, flag)+result;
					  flag =  checkUnder(first.storage[i]-'0', 0, flag);
				  }
				  
				  
			  }else {
				  if(flag ==1) {
					  result = partSubtract(first.storage[i]-'0', second.storage[i-(first.numLength - second.numLength)]-'0', flag)+result;
					  flag =  checkUnder(first.storage[i]-'0', second.storage[i-(first.numLength - second.numLength)]-'0', flag);
				  }else{
					  result = partSubtract(first.storage[i]-'0', second.storage[i-(first.numLength - second.numLength)]-'0', flag)+result;
					  flag =  checkUnder(first.storage[i]-'0', second.storage[i-(first.numLength - second.numLength)]-'0', flag);
				  }
			  }
		  }
		  
	  }else if (compareFlag ==2){ 
		  for(int i=second.numLength-1; i>=0; i--) {
			  if (i < second.numLength - first.numLength) {
				  result = partSubtract(second.storage[i]-'0', 0, flag)+result;
				  flag =  checkUnder(second.storage[i]-'0', 0, flag);
			  }else {
				  result = partSubtract(second.storage[i]-'0', first.storage[i-(second.numLength - first.numLength)]-'0', flag)+result;
				  flag =  checkUnder(second.storage[i]-'0', first.storage[i-(second.numLength - first.numLength)]-'0', flag);
			  }
		  }
		  
	  }else if (compareFlag ==3) {
		  result = "0";
	  }
	  
	 
	  int cnt = -1;
	  
	  for (int i=0; i<result.length(); i++) {
		  cnt+=1;
		  if (i==result.length()-1) { 
			  break;
		  }
		  if (result.charAt(i)-'0' != 0) {
			  break;
		  }
	  }
	  result = result.substring(cnt);
	  return result;
  }
  
  
  
  // returns 'this' + 'opnd'; Both inputs remain intact.
  
  public LongInt add(LongInt opnd) {
	  
	  int signFlag = checkSign(this.sign,opnd.sign);
	  int compareFlag = compareAbs(this,opnd);
	  boolean ansSign=true;
	  String result ="";
	  
	  if (signFlag ==1) {
		  result = absAdd(this,opnd);
	  }else if(signFlag ==2){
		  result = absAdd(this,opnd);
		  ansSign = false;
	  }else if(signFlag ==3){
		  result = absSubtract(this,opnd);
		  if (compareFlag==2) {
			  ansSign = false;
		  }
	  }else if(signFlag ==4){
		 result = absSubtract(this,opnd) ;
		  if (compareFlag==1) {
			  ansSign = false ;
		  }
	  }
	  
	  LongInt answer = new LongInt(result);
	  answer.sign = ansSign;
	  return answer;
  }

  // returns 'this' - 'opnd'; Both inputs remain intact.
  public LongInt subtract(LongInt opnd) { 
	  
	  int signFlag = checkSign(this.sign,opnd.sign);
	  int compareFlag = compareAbs(this,opnd);
	  boolean ansSign=true;
	  String result ="";
	  
	  if (signFlag ==1) {
		  result = absSubtract(this,opnd);
		  if (compareFlag == 2) {
			  ansSign=false;
		  }
	  }else if(signFlag ==2){
		  result = absSubtract(this,opnd) ;
		  if (compareFlag == 1) {
			  ansSign=false;
		  }
	  }else if(signFlag ==3){
		  result = absAdd(this,opnd);
	  }else if(signFlag ==4){
		  result = absAdd(this,opnd);
		  ansSign = false;
	  }
	  LongInt answer = new LongInt(result);
	  answer.sign= ansSign;
	  return answer;
  }
  // returns 'this' * 'opnd'; Both inputs remain intact.
  public LongInt multiply(LongInt opnd) {  
	  
	  int signFlag = checkSign(this.sign,opnd.sign);

	  boolean ansSign=true;
	  String[] temp = new String[opnd.numLength];
	  if (signFlag == 3||signFlag==4) {ansSign =false;}
	  if (this.asString.equals("0") || opnd.asString.equals("0") ){
		  ansSign =true;
	  }
	  for (int i=opnd.numLength-1; i>=0;i--) {
		  temp[i] = repeatAddForMul(this.asString, opnd.storage[i]-'0')+"0".repeat((opnd.numLength-1)-i);
	  }
	  String resultMul = arrAddForMul(temp);
	  LongInt answer = new LongInt(resultMul);
	  answer.sign = ansSign;
	  return answer;
  }

  // print the value of 'this' element to the standard output.
  public void print() {
	  String signString = (this.sign)? "":"-";
	  System.out.print(signString);
	  for(int i=0; i<this.numLength; i++)
		  System.out.print(this.storage[i]);
  }

}