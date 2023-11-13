package christmas.global.view.io;

import christmas.global.view.output.OutputView;

import java.util.function.Supplier;

public interface InteractionRepeatable {

    default void runInteraction(final Runnable runnable) {
        while (true) {
            try {
                runnable.run();
                break;
            } catch (IllegalArgumentException e) {
                OutputView.println(e.getMessage());
            }
        }
    }

    default <T> T supplyInteraction(final Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.println(e.getMessage());
            }
        }
    }
}
