package com.arm;

import java.text.ParseException;

class CommandFactory {
    /**
     * Returns a command defined by the parsed input.
     *
     * @param inputRecord the input record
     * @return a Command instance defined by inputRecord or null if inputRecord is not formatted as expected
     */
    static Command parse(String inputRecord) {
        String[] inputRecordParts = inputRecord.split("\t");
        try {
            int time = Integer.parseInt(inputRecordParts[0]);
            String commandName = inputRecordParts[1];

            switch (commandName) {
                case "ADD":
                    return parseAddCommand(inputRecordParts, time);
                case "MODIFY":
                    return parseModifyCommand(inputRecordParts, time);
                case "CANCEL":
                    return parseCancelCommand(inputRecordParts, time);
                case "RESET":
                    return new ResetCommand(time);
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    private static Command parseAddCommand(String[] inputRecordParts, int time) throws ParseException {
        String id = inputRecordParts[2];

        String stringSide = inputRecordParts[3];
        OrderSide side;
        if (stringSide.compareTo("B") == 0) {
            side = OrderSide.BUY;
        } else if (stringSide.compareTo("S") == 0) {
            side = OrderSide.SELL;
        } else {
            throw new ParseException("Invalid order side.", 3);
        }

        int size = Integer.parseInt(inputRecordParts[4]);
        int price = Integer.parseInt(inputRecordParts[5]);

        return new AddOrderCommand(new Order(time, id, side, size, price));
    }

    private static Command parseModifyCommand(String[] inputRecordParts, int time) {
        String id = inputRecordParts[2];
        int size = Integer.parseInt(inputRecordParts[3]);
        int price = Integer.parseInt(inputRecordParts[4]);
        return new ModifyOrderCommand(time, id, size, price);
    }

    private static Command parseCancelCommand(String[] inputRecordParts, int time) {
        String id = inputRecordParts[2];
        return new CancelOrderCommand(time, id);
    }
}
