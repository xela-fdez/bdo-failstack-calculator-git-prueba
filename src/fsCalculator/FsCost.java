package fsCalculator;

import java.text.DecimalFormat;

public class FsCost {									//Variables of the clase are:
	private int fs;										//Failstack wanted to be calculated
	private int reblath;								//The price of the cheapest reblath to use
	private Double cost;								//Cost taking into account repair items and consumed items on enhance
	private Double costDowngrade;						//Cost taking into account the price of each item used, 
														//the price of a TRI Grunil, the DUO Grunils needed for that one and so on
	
	private double accPerPri;							//In groups of five and in order, chance of Accesories, Boss Gear,
	private double accPerDuo;							//Blackstar Gear and Fallen God Gear from PRI to PEN
	private double accPerTri;
	private double accPerTet;
	private double accPerPen;
	private double bosPerPri;
	private double bosPerDuo;
	private double bosPerTri;
	private double bosPerTet;
	private double bosPerPen;
	private double blsPerPri;
	private double blsPerDuo;
	private double blsPerTri;
	private double blsPerTet;
	private double blsPerPen;
	private double falPerPri;
	private double falPerDuo;
	private double falPerTri;
	private double falPerTet;
	private double falPerPen;
	private DecimalFormat chanceFormat;				//So numbers look cute if used without the UI
	private DecimalFormat bigNumberFormat;
	
	
	FsCost(int fs) {
		reblath=12900;
		this.fs=fs;
		chanceFormat = new DecimalFormat("#.00");
		bigNumberFormat = new DecimalFormat("#,###");
		
		//All the calculations are done below, each "block" in pseudocode would be:
		//If the failstack is lesser than it's softcap then it will be the base chance at 0 fs + the chance per failstack times the failstack
		//If it's over the failstack the chance is the same before softcap plus the after-softcap chance times the remaining failstacks
		//And as the hardcap is 90%, if it is greater than 90% it just displays 90%
		
		if(fs<19)accPerPri=0.25+0.025*fs;						//Acc		//Pri %
		else accPerPri=0.25+0.025*18+0.005*(fs-18);
		if(accPerPri>0.90)accPerPri=0.90;
		
		if(fs<50)bosPerPri=0.1176+0.01176*fs;					//Boss
		else bosPerPri=0.1176+0.01176*50+0.0023525*(fs-50);
		if(bosPerPri>0.90)bosPerPri=0.90;
		
		if(fs<44)blsPerPri=0.1308+0.013795*fs;					//Blackstar
		else blsPerPri=0.1308+0.013795*44+0.00261621*(fs-44);
		if(blsPerPri>0.90)blsPerPri=0.90;
		
		if(fs<340)falPerPri=0.02+0.002*fs;						//Fallen God
		else falPerPri=0.02+0.002*340+0.0004*(fs-340);
		if(falPerPri>0.90)falPerPri=0.90;
		
		if(fs<41)accPerDuo=0.1+0.01*fs;							//Acc		//Duo %
		else accPerDuo=0.1+0.01*40+0.002*(fs-40);
		if(accPerDuo>0.90)accPerDuo=0.90;
		
		if(fs<82)bosPerDuo=0.0769+(0.6306/82)*fs;				//Boss
		else bosPerDuo=0.0769+(63.06/82)*82+0.001538*(fs-82);
		if(bosPerDuo>0.90)bosPerDuo=0.90;
		
		if(fs<56)blsPerDuo=0.1063+(0.5953/56)*fs;				//Blackstar
		else blsPerDuo=0.1063+(59.53/56)*56+(0.1594/75)*(fs-56);
		if(blsPerDuo>0.90)blsPerDuo=0.90;
		
		if(fs<690)falPerDuo=0.01+0.001*fs;						//Fallen God
		else falPerDuo=0.01+0.001*690+0.0002*(fs-690);
		if(falPerDuo>0.90)falPerDuo=0.90;
		
		if(fs<45)accPerTri=0.075+0.0075*fs;						//Acc		//Tri %
		else accPerTri=0.075+0.0075*44+0.0015*(fs-44);
		if(accPerTri>0.90)accPerTri=0.90;
		
		if(fs<102)bosPerTri=0.0625+(0.6375/102)*fs;				//Boss
		else bosPerTri=0.0625+(0.6375/102)*102+0.00125*(fs-102);
		if(bosPerTri>0.90)bosPerTri=0.90;
		
		if(fs<196)blsPerTri=0.034+(0.6664/196)*fs;				//Blackstar
		else blsPerTri=0.034+(0.6664/196)*196+0.00068*(fs-196);
		if(blsPerTri>0.90)blsPerTri=0.90;
		
		if(fs<1390)falPerTri=0.005+(0.695/1390)*fs;				//Fallen God
		else falPerTri=0.005+(0.695/1390)*1390+(0.01/100)*(fs-1390);
		if(falPerTri>0.90)falPerTri=0.90;
		
		if(fs<111)accPerTet=0.025+0.0025*fs;					//Acc		//Tet %
		else accPerTet=0.025+0.0025*110+0.0005*(fs-110);
		if(accPerTet>0.90)accPerTet=0.90;
		
		if(fs<340)bosPerTet=0.02+(0.68/340)*fs;					//Boss
		else bosPerTet=0.02+(0.68/340)*340+(0.04/100)*(fs-340);
		if(bosPerTet>0.90)bosPerTet=0.90;
		
		if(fs<1363)blsPerTet=0.0051+(0.6951/1363)*fs;			//Blackstar
		else blsPerTet=0.0051+(0.6951/1363)*1363+0.000102*(fs-196);
		if(blsPerTet>0.90)blsPerTet=0.90;
		
		if(fs<3490)falPerTet=0.002+0.0002*fs;					//Fallen God
		else falPerTet=0.002+0.0002*3490+0.00004*(fs-3490);
		if(falPerTet>0.90)falPerTet=0.90;
		
		if(fs<491)accPerPen=0.005+0.0005*fs;					//Acc		//Pen %
		else accPerPen=0.005+0.0005*490+0.0001*(fs-490);
		if(accPerPen>0.90)accPerPen=0.90;
		
		if(fs<2324)bosPerPen=0.003+(0.6972/2324)*fs;			//Boss
		else bosPerPen=0.003+(0.6972/2324)*2324+0.00006*(fs-2324);
		if(bosPerPen>0.90)bosPerPen=0.90;
		
		if(fs<3490)blsPerPen=0.002+0.0002*fs;					//Blackstar
		else blsPerPen=0.002+0.0002*3490+0.00004*(fs-3490);
		if(blsPerPen>0.90)blsPerPen=0.90;
		
		if(fs<279300)falPerPen=0.000025+(0.01/3990)*fs;			//Fallen God
		else falPerPen=0.000025+(1/3990)*279300+(0.00001/20)*(fs-279300);
		if(falPerPen>0.90)falPerPen=0.90;
	}
	
	@Override
	public String toString() {												//This toString method was when the project was about
		return "With "+fs+" failstacks the odds are:"						//just accesories, now it's "obsolette" as it now has all 
				+ "\nPRI: " + chanceFormat.format(accPerPri*100) + "%"		//type of gear and a User Interface
				+ "\nDUO: " + chanceFormat.format(accPerDuo*100) + "%"
				+ "\nTRI: " + chanceFormat.format(accPerTri*100) + "%"
				+ "\nTET: " + chanceFormat.format(accPerTet*100) + "%"
				+ "\nPEN: " + chanceFormat.format(accPerPen*100) + "%";
	}
	
	//The method costFs calculates the cost just taking into account enhancing materials and choosing the bracket it fits better
	//this brackets are when I personally change the item I use to failstack
	public void costFs(int bArmor, int concArmor, int grunil, int mem, int baseFs) {
		if(fs<=20) cost = (double) ((fs-baseFs)*(bArmor+reblath/2));									//Works good?
		else if(fs<=32) cost = (double) ((21-(3-((fs-21)%3))-baseFs)*(bArmor+reblath/2)    +    ((fs-21)/3+1)*(concArmor+grunil)); //blackstones*bs price  +  concs*conc price
		else if(fs<=48) cost = (double) ((21-(4-((fs-33)%4))-baseFs)*(bArmor+reblath/2)+4*(concArmor+grunil)+((fs-33)/4+1)*(concArmor+grunil)); //blackstones*bs price + previosConcs*conc price + newConcs* conc price
		else if(fs<=93) cost = (double) ((21-(5-((fs-49)%5))-baseFs)*(bArmor+reblath/2)+8*(concArmor+grunil)+((fs-49)/5+1)*(concArmor+grunil));
		else if(fs>93){ cost = (double) ((21-(6-((fs-94)%6))-baseFs)*(bArmor+reblath/2)+17*(concArmor+grunil));
			cost+= (double)(((fs-(93))/6)+1)*(concArmor+mem*10);
		}
//		if(fs<=20) cost = (double) ((fs-baseFs)*bArmor+(fs-baseFs)*reblath/2);							//Works wrong
//		else if(fs<=32) cost = (double) ((fs-baseFs-(fs%3))*(bArmor+reblath/2)+((fs-(20-(fs%3)))/3)*(concArmor+grunil));
//		else if(fs<=48) cost = (double) ((fs-baseFs-(fs%4))*(bArmor+reblath/2)+4*(concArmor+grunil)+((fs-(32-(fs%4)))/4)*(concArmor+grunil));
//		else if(fs<=93) cost = (double) ((fs-baseFs-(fs%5))*(bArmor+reblath/2)+8*(concArmor+grunil)+((fs-(48-(fs%5)))/5)*(concArmor+grunil));
//		else if(fs>93) { cost = (double) ((fs-baseFs-(fs%6))*(bArmor+reblath/2)+17*(concArmor+grunil));
//		cost+= (double)((fs-(93-(fs%6)))/6)*(concArmor+mem*10);		
//		}
	}
	
	//This method extends the previous method of costFs taking into account the cost of upgrading each Grunil Helmet to each
	//level as you probably won't have them already made sitting in the storage.
	//The average of tries used to get each DUO, TRI or TET is just the value in the middle instead of the actual average clicks
	//as it is some work I don't feel like doing right now :)
	//In example, if there are 4 taps when I have to use the DUO items, it will take the average to get a DUO Grunil as 2.5, not the
	//actual chance of getting a DUO Grunil in that range of stacks
	//**UPDATE**
	//Doing some rough numbers I chose some failstacks that seem more aproppiate
	
	
	public void costFsCostDowngrades(int bArmor, int concArmor, int grunil, int mem, int baseFs) {
		
		
		
		if (fs<=32) {
			costFs(bArmor, concArmor, grunil, mem, baseFs);
			costDowngrade=cost;	
		}
		else if (fs<=48) {		
			int duoTaps=((fs-33)/4+1);
			costDowngrade = cost+costDuoGrunil(bArmor, concArmor, grunil, mem, baseFs)*duoTaps;
		}
		else if (fs<=93) {
			int triTaps=((fs-49)/5+1);
			costDowngrade = cost+costTriGrunil(bArmor, concArmor, grunil, mem, baseFs)*triTaps;		
			costDowngrade+=costDuoGrunil(bArmor, concArmor, grunil, mem, baseFs)*4;
		}
		else {
			int tetTaps=((fs-94)/6+1);
			costDowngrade = cost+costTetGrunil(bArmor, concArmor, grunil, mem, baseFs)*tetTaps;
			costDowngrade+=costTriGrunil(bArmor, concArmor, grunil, mem, baseFs)*4;
			costDowngrade+=costDuoGrunil(bArmor, concArmor, grunil, mem, baseFs)*4;
		}
	}
	
	//The costXxxGrunil are just so it is easier to calculate the previously mentioned costs per piece
	public double costDuoGrunil(int bArmor, int concArmor, int grunil, int mem, int baseFs) {
		FsCost fsDuo = new FsCost(31);
		fsDuo.costFs(bArmor, concArmor, grunil, mem, baseFs);
		return fsDuo.getCost();
	}
	
	public double costTriGrunil(int bArmor, int concArmor, int grunil, int mem, int baseFs) {
		FsCost fsTri = new FsCost(45);
		fsTri.costFs(bArmor, concArmor, grunil, mem, baseFs);
		return fsTri.getCost()+4*costDuoGrunil(bArmor, concArmor, grunil, mem, baseFs);
	}
	
	public double costTetGrunil(int bArmor, int concArmor, int grunil, int mem, int baseFs) {
		FsCost fsTet = new FsCost(79);
		fsTet.costFs(bArmor, concArmor, grunil, mem, baseFs);
		return fsTet.getCost()+4*costTriGrunil(bArmor, concArmor, grunil, mem, baseFs)+4*costDuoGrunil(bArmor, concArmor, grunil, mem, baseFs);
	}
	
	public int[] fsPerStage() {
		int stages[]=new int[4];
		if(fs>93) {
			stages[0]=(21-(6-((fs-94)%6))-1);
			stages[1]=stages[0]+12;
			stages[2]=stages[1]+12;
			stages[3]=stages[2]+50;
		}
		else if(fs>48) {
			stages[0]=(21-(5-((fs-49)%5))-1);
			stages[1]=stages[0]+12;
			stages[2]=stages[1]+12;
		}
		else if(fs>32) {
			stages[0]=(21-(4-((fs-33)%4))-1);
			stages[1]=stages[0]+12;
		}
		else if(fs>20) {
			stages[0]=(21-(3-((fs-21)%3))-1);
		}
		return stages;
	}
	
	//The two text silver cost text formatted on a String
	public String textCost() {
		return bigNumberFormat.format(cost);
	}
	
	public String textCostDowngrade() {
		return bigNumberFormat.format(costDowngrade);
	}
	
	//All the getters for prices and chances
	public double getCost() {
		return cost;
	}
	
	public double getCostDowngrade() {
		return costDowngrade;
	}
	
	public double getAccPerPri() {
		return accPerPri;
	}
	
	public double getAccPerDuo() {
		return accPerDuo;
	}
	
	public double getAccPerTri() {
		return accPerTri;
	}
	
	public double getAccPerTet() {
		return accPerTet;
	}
	
	public double getAccPerPen() {
		return accPerPen;
	}
	
	public double getBosPerPri() {
		return bosPerPri;
	}

	public double getBosPerDuo() {
		return bosPerDuo;
	}

	public double getBosPerTri() {
		return bosPerTri;
	}

	public double getBosPerTet() {
		return bosPerTet;
	}

	public double getBosPerPen() {
		return bosPerPen;
	}

	public double getBlsPerPri() {
		return blsPerPri;
	}

	public double getBlsPerDuo() {
		return blsPerDuo;
	}

	public double getBlsPerTri() {
		return blsPerTri;
	}

	public double getBlsPerTet() {
		return blsPerTet;
	}

	public double getBlsPerPen() {
		return blsPerPen;
	}

	public double getFalPerPri() {
		return falPerPri;
	}

	public double getFalPerDuo() {
		return falPerDuo;
	}

	public double getFalPerTri() {
		return falPerTri;
	}

	public double getFalPerTet() {
		return falPerTet;
	}

	public double getFalPerPen() {
		return falPerPen;
	}
	
}
