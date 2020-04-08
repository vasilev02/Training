package aquarium.models.aquariums;

import aquarium.models.fish.Fish;

public class FreshwaterAquarium extends BaseAquarium {
    public FreshwaterAquarium(String name) {
        super(name,50);
    }


    @Override
    public String getInfo() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s (FreshwaterAquarium):", getName()));
        sb.append(System.lineSeparator());

        sb.append("Fish: ");

        if (this.getFish().size() == 0) {
            sb.append("none");

        }else{
            for (Fish current : getFish()) {
                sb.append(current.getName() + " ");
            }
        }

        sb.append(System.lineSeparator());

        sb.append("Decorations: " + this.getDecorations().size());
        sb.append(System.lineSeparator());

        sb.append("Comfort: " + this.calculateComfort());

        sb.append(System.lineSeparator());
        return sb.toString().trim();
    }
}
