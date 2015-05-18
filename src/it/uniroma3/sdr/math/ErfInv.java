package it.uniroma3.sdr.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Classe per il calcolo dell'inversa della funzione degli errori.
 * 
 * @author emiliano
 *
 */
public class ErfInv {
	
	/**
	 * @param x	Input della funzione
	 * @return	Una approssimazione di inverf(x)
	 * @throws RuntimeException	Se l'argomento non appartiene al range [-1, 1]
	 */
	public static double evaluate(double x) throws RuntimeException {

		if (Math.abs(x) > 1) {
			throw new RuntimeException("Allowed values for argument in [-1, 1]");
		}

		if (Math.abs(x) == 1) {
			return (x == -1 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
		}

		else {
			if (x == 0) {
				return 0;
			}

			BigDecimal bd = new BigDecimal(0, MathContext.UNLIMITED);
			BigDecimal bd1 = new BigDecimal(x*Math.sqrt(Math.PI)/2,MathContext.UNLIMITED);

			String[] A092676 = {"1", "1", "7", "127", "4369", "34807",
					"20036983", "2280356863", 
					"49020204823", "65967241200001",
					"15773461423793767",
					"655889589032992201",
					"94020690191035873697", "655782249799531714375489",
					"44737200694996264619809969",
					"10129509912509255673830968079", "108026349476762041127839800617281",
					"10954814567103825758202995557819063",
					"61154674195324330125295778531172438727",
					"54441029530574028687402753586278549396607",
					"452015832786609665624579410056180824562551",
					"2551405765475004343830620568825540664310892263",
					"70358041406630998834159902148730577164631303295543",
					"775752883029173334450858052496704319194646607263417",
					"132034545522738294934559794712527229683368402215775110881"
			};
			
			String[] A092677 = {"1", "3", "30", "630", "22680", "178200",
					"97297200", "10216206000", 
					"198486288000", "237588086736000",
					"49893498214560000", 
					"1803293578326240000",
					"222759794969712000000","1329207696584271504000000",
					"77094046401887747232000000",
					"14761242414008506896480000000", "132496911908140357902804480000000",
					"11262237512191930421738380800000000",
					"52504551281838779626144331289600000000",
					"38905872499842535702972949485593600000000",
					"268090886133368733415443853598208000000000",
					"1252532276140582782027102181569679872000000000",
					"28520159927721069946757116674341610685440000000000",
					"259078091444256105986928093487086396226560000000000",
					"36256424429074976496234665114956818633529712640000000000"
			};
			
			for (int i = 0; i < A092676.length; i++) {                
				BigDecimal num = new BigDecimal(new BigInteger(A092676[i]), 50);
				BigDecimal den = new BigDecimal(new BigInteger(A092677[i]), 50);
				BigDecimal coeff = num.divide(den, RoundingMode.HALF_UP);
				BigDecimal xBD = bd1.pow(i*2+1, MathContext.UNLIMITED);           
				bd = bd.add(xBD.multiply(coeff, MathContext.UNLIMITED));       
			}            

			return bd.doubleValue();
		}
	}
}
