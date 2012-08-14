package common.types;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author benjamin
 */
public enum Symbol {
    /**
     * Reserved slot
     */
    _SLOT0 {
        @Override
        public String toString() {
            String str = _sym2Name.get(this);
            if (str == null) {
                str = super.toString();
            }
            return str;
        }
    },
    /**
     * Reserved slot
     */
    _SLOT1 {
        @Override
        public String toString() {
            String str = _sym2Name.get(this);
            if (str == null) {
                str = super.toString();
            }
            return str;
        }
    },

    /**
     * Reserved slot
     */
    _SLOT2 {
        @Override
        public String toString() {
            String str = _sym2Name.get(this);
            if (str == null) {
                str = super.toString();
            }
            return str;
        }
    };

    private static int _slotIdx = 0;
    private static Map<String, Symbol> _name2Sym = new HashMap<>();
    private static EnumMap<Symbol, String> _sym2Name = new EnumMap<>(
            Symbol.class);

    private static Symbol createSlot(String symName) {
        Symbol slot = null;
        try {
            String slotName = "_SLOT" + _slotIdx;
            slot = Symbol.valueOf(slotName);
            _slotIdx++;
            _name2Sym.put(symName, slot);
            _sym2Name.put(slot, symName);
        } catch (IllegalArgumentException e1) {
            throw new RuntimeException("Maximum number of slots reached: "
                    + _slotIdx);
        }
        return slot;
    }

    /**
     * Will return a symbol enum that matches this name. If none is found, it
     * will use one of the reserved slots and map it to this name.
     * 
     * @param symName symbol name
     * @return enum corresponding to <code>symName</code>
     */
    public synchronized static Symbol reserve(String symName) {
        try {
            return Symbol.valueOf(symName);
        } catch (IllegalArgumentException e) {
            Symbol sym = _name2Sym.get(symName);
            if (sym == null) {
                sym = createSlot(symName);
            }
            return sym;
        }
    }

    /**
     * Returns the corresponding symbol enum for <code>symName</code>. Use this
     * instead of valueOf() if you want to take advantage of reserved slots.
     * 
     * @param symName symbol name
     * @return symbol enum corresponding to <code>symName</code>
     */
    public synchronized static Symbol resolve(String symName) {
        Symbol sym = _name2Sym.get(symName);
        if (sym == null) {
            sym = Symbol.valueOf(symName);
        }
        return sym;
    }

    /**
     * Clears all the used slots and makes them available
     */
    public synchronized static void clearSlots() {
        _name2Sym.clear();
        _sym2Name.clear();
        _slotIdx = 0;
    }
}
