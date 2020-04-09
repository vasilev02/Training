package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;

public class Retire extends Command {
    protected Retire(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {

        String result;

        try {

            this.getRepository().removeUnit(this.getData()[1]);
            result = this.getData()[1]+" retired!";
        }catch (IllegalArgumentException e){
            result = e.getMessage();
        }

        return result;

    }
}
