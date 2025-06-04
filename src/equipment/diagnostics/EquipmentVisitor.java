package equipment.diagnostics;

import equipment.*;

public interface EquipmentVisitor {
    void visit(Burner burner);
    void visit(Cauldron cauldron);
    void visit(StirringRod rod);
    void visit(Vial vial);
}
