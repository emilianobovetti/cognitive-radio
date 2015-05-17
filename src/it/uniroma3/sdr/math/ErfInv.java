package it.uniroma3.sdr.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class ErfInv {
	public static double evaluate(double d) throws RuntimeException {

		if (Math.abs(d) > 1) {
			throw new RuntimeException("Allowed values for argument in [-1,1]");
		}

		if (Math.abs(d) == 1) {
			return (d == -1 ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
		}

		else {
			if (d == 0) {
				return 0;
			}

			BigDecimal bd = new BigDecimal(0, MathContext.UNLIMITED);
			BigDecimal x = new BigDecimal(d*Math.sqrt(Math.PI)/2,MathContext.UNLIMITED);

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
				BigDecimal xBD = x.pow(i*2+1, MathContext.UNLIMITED);           
				bd = bd.add(xBD.multiply(coeff, MathContext.UNLIMITED));       
			}            

			return bd.doubleValue();
		}
	}
	
	// Apache Commons ErfInv
	public static double apacheCommonEvaluate(final double x) {
		// beware that the logarithm argument must be
		// commputed as (1.0 - x) * (1.0 + x),
		// it must NOT be simplified as 1.0 - x * x as this
		// would induce rounding errors near the boundaries +/-1
		double w = - Math.log((1.0 - x) * (1.0 + x));
		double p;

		if (w < 6.25) {
			w -= 3.125;
			p =  -3.6444120640178196996e-21;
			p =   -1.685059138182016589e-19 + p * w;
			p =   1.2858480715256400167e-18 + p * w;
			p =    1.115787767802518096e-17 + p * w;
			p =   -1.333171662854620906e-16 + p * w;
			p =   2.0972767875968561637e-17 + p * w;
			p =   6.6376381343583238325e-15 + p * w;
			p =  -4.0545662729752068639e-14 + p * w;
			p =  -8.1519341976054721522e-14 + p * w;
			p =   2.6335093153082322977e-12 + p * w;
			p =  -1.2975133253453532498e-11 + p * w;
			p =  -5.4154120542946279317e-11 + p * w;
			p =    1.051212273321532285e-09 + p * w;
			p =  -4.1126339803469836976e-09 + p * w;
			p =  -2.9070369957882005086e-08 + p * w;
			p =   4.2347877827932403518e-07 + p * w;
			p =  -1.3654692000834678645e-06 + p * w;
			p =  -1.3882523362786468719e-05 + p * w;
			p =    0.0001867342080340571352 + p * w;
			p =  -0.00074070253416626697512 + p * w;
			p =   -0.0060336708714301490533 + p * w;
			p =      0.24015818242558961693 + p * w;
			p =       1.6536545626831027356 + p * w;
		} else if (w < 16.0) {
			w = Math.sqrt(w) - 3.25;
			p =   2.2137376921775787049e-09;
			p =   9.0756561938885390979e-08 + p * w;
			p =  -2.7517406297064545428e-07 + p * w;
			p =   1.8239629214389227755e-08 + p * w;
			p =   1.5027403968909827627e-06 + p * w;
			p =   -4.013867526981545969e-06 + p * w;
			p =   2.9234449089955446044e-06 + p * w;
			p =   1.2475304481671778723e-05 + p * w;
			p =  -4.7318229009055733981e-05 + p * w;
			p =   6.8284851459573175448e-05 + p * w;
			p =   2.4031110387097893999e-05 + p * w;
			p =   -0.0003550375203628474796 + p * w;
			p =   0.00095328937973738049703 + p * w;
			p =   -0.0016882755560235047313 + p * w;
			p =    0.0024914420961078508066 + p * w;
			p =   -0.0037512085075692412107 + p * w;
			p =     0.005370914553590063617 + p * w;
			p =       1.0052589676941592334 + p * w;
			p =       3.0838856104922207635 + p * w;
		} else if (!Double.isInfinite(w)) {
			w = Math.sqrt(w) - 5.0;
			p =  -2.7109920616438573243e-11;
			p =  -2.5556418169965252055e-10 + p * w;
			p =   1.5076572693500548083e-09 + p * w;
			p =  -3.7894654401267369937e-09 + p * w;
			p =   7.6157012080783393804e-09 + p * w;
			p =  -1.4960026627149240478e-08 + p * w;
			p =   2.9147953450901080826e-08 + p * w;
			p =  -6.7711997758452339498e-08 + p * w;
			p =   2.2900482228026654717e-07 + p * w;
			p =  -9.9298272942317002539e-07 + p * w;
			p =   4.5260625972231537039e-06 + p * w;
			p =  -1.9681778105531670567e-05 + p * w;
			p =   7.5995277030017761139e-05 + p * w;
			p =  -0.00021503011930044477347 + p * w;
			p =  -0.00013871931833623122026 + p * w;
			p =       1.0103004648645343977 + p * w;
			p =       4.8499064014085844221 + p * w;
		} else {
			// this branch does not appears in the original code, it
			// was added because the previous branch does not handle
			// x = +/-1 correctly. In this case, w is positive infinity
			// and as the first coefficient (-2.71e-11) is negative.
			// Once the first multiplication is done, p becomes negative
			// infinity and remains so throughout the polynomial evaluation.
			// So the branch above incorrectly returns negative infinity
			// instead of the correct positive infinity.
			p = Double.POSITIVE_INFINITY;
		}
		return p * x;
	}
}
