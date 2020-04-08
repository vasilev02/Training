package santasWorkshop.models;

public class WorkshopImpl implements Workshop {

    public WorkshopImpl() {
    }

    @Override
    public void craft(Present present, Dwarf dwarf) {

        if (dwarf.canWork() && dwarf.getInstruments().size() > 0) {

        } else {
            return;
        }


        for (Instrument instrument : dwarf.getInstruments()) {

            if(instrument.isBroken()){
                continue;
            }

            if(!dwarf.canWork()){
                return;
            }

            while (!present.isDone()){

                instrument.use();
                dwarf.work();
                present.getCrafted();

                if (present.isDone()) {
                    return;
                }
                if(instrument.isBroken()){
                    break;
                }

                if(!dwarf.canWork()){
                    return;
                }

            }

        }


    }
}
