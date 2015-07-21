package au.edu.adelaide.pna.system;

/**
 * This class receives the environmental information form Information Service,
 * then quantizes it into a number that represent the level of granularity of 
 * provenance information that need to be collected. 
 * 
 * @author Mehdi Sarikhani
 *
 */
public class InformationServiceQuantizer {
	
	public InformationServiceQuantizer(){
		
	} 

	/*
	 * granularityLevel:
	 * 
	 *   0 = fine-grained
	 *   1 = medium-grained
	 *   2 = coarse-grained 
	 */
	public int granularityLevel = 0;
	
	public int getGranularityLevel() {
		return granularityLevel;
	}
	public void setGranularityLevel(int granularityLevel) {
		this.granularityLevel = granularityLevel;
	}
}