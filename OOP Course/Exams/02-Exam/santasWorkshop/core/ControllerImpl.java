package santasWorkshop.core;

import santasWorkshop.common.ConstantMessages;
import santasWorkshop.common.ExceptionMessages;
import santasWorkshop.models.*;
import santasWorkshop.repositories.DwarfRepository;
import santasWorkshop.repositories.PresentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private DwarfRepository dwarfRepository;
    private PresentRepository presentRepository;
    private int craftedPresents = 0;
    private int brokenInstruments = 0;

    public ControllerImpl() {
        this.dwarfRepository = new DwarfRepository();
        this.presentRepository = new PresentRepository();
    }

    @Override
    public String addDwarf(String type, String dwarfName) {

        Dwarf dwarf = null;

        if (type.equals("Sleepy")){
            dwarf = new Sleepy(dwarfName);
            this.dwarfRepository.add(dwarf);
            return String.format(ConstantMessages.ADDED_DWARF,type,dwarfName);

        }else if(type.equals("Happy")){
            dwarf = new Happy(dwarfName);
            this.dwarfRepository.add(dwarf);
            return String.format(ConstantMessages.ADDED_DWARF,type,dwarfName);

        }else{
            throw new IllegalArgumentException(ExceptionMessages.DWARF_TYPE_DOESNT_EXIST);
        }

    }

    @Override
    public String addInstrumentToDwarf(String dwarfName, int power) {

        Instrument instrument = new InstrumentImpl(power);

        for (Dwarf dwarf : this.dwarfRepository.getModels()) {
            if(dwarf.getName().equals(dwarfName)){
                dwarf.addInstrument(instrument);
                return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_DWARF
                ,power,dwarfName);
            }
        }
        throw new IllegalArgumentException(ExceptionMessages.DWARF_DOESNT_EXIST);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {

        Present present = new PresentImpl(presentName,energyRequired);

        this.presentRepository.add(present);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT,presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        List<Dwarf> workers = this.dwarfRepository.getModels().stream().filter(d -> d.getEnergy() > 50)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();


        if(workers.size()==0){
            throw new IllegalArgumentException(ExceptionMessages.NO_DWARF_READY);
        }

        Present present = this.presentRepository.getModels().stream().filter(p -> p.getName().equals(presentName))
                .findFirst().orElse(null);

        //TODO IMPLEMENT LOGIC TO DEAL WITH PRESENT


        Workshop workshop = new WorkshopImpl();
        boolean checker = false;

        for (Dwarf worker : workers) {

            workshop.craft(present,worker);

            if(present.isDone()){
                checker = true;
                this.craftedPresents++;
                break;
            }

        }

        for (Dwarf current : this.dwarfRepository.getModels()) {
            for (Instrument instrument : current.getInstruments()) {
                if(instrument.isBroken()){
                    this.brokenInstruments++;
                }
            }
        }

        if(checker==false){
            sb.append(String.format(ConstantMessages.PRESENT_DONE,presentName,"not done"));
            sb.append(String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS,this.brokenInstruments));

            return sb.toString().trim();
        }else{
            sb.append(String.format(ConstantMessages.PRESENT_DONE,presentName,"done"));
            sb.append(String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS,this.brokenInstruments));

            return sb.toString().trim();
        }




    }

    @Override
    public String report() {

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%d presents are done!",this.craftedPresents));
        sb.append(System.lineSeparator());

        sb.append("Dwarfs Info:");
        sb.append(System.lineSeparator());

        for (Dwarf dwarf : this.dwarfRepository.getModels()) {
            sb.append(String.format("Name: %s",dwarf.getName()));
            sb.append(System.lineSeparator());

            sb.append(String.format("Energy: %d",dwarf.getEnergy()));
            sb.append(System.lineSeparator());

            int counter=0;
            for (Instrument instrument : dwarf.getInstruments()) {
                if(!instrument.isBroken()){
                    counter++;
                }
            }

            sb.append(String.format("Instruments %d not broken left",counter));
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
