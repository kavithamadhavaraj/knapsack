package fillbag;
//An Item class with the specified attributes like index, weight and cost. Along with the usual getters and setters.
public class Item{
    int index;
    float weight;
    float cost;
    public int getIndex(){
        return this.index;
    }
    public float getWeight(){
        return this.weight;
    }
    public float getCost(){
        return this.cost;
    }
    public void setIndex(int index){
        this.index = index;
    }
    public void setWeight(float weight){
        this.weight = weight;
    }
    public void setCost(float cost){
        this.cost = cost; 
    }
    
}
