package equipment;

import equipment.diagnostics.EquipmentVisitor;

public interface Equipment {
    public void accept(EquipmentVisitor visitor);
}
