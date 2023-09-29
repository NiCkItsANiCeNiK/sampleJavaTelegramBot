package bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class SampleBotClass extends TelegramLongPollingBot {
    SendMessage sendMessage = new SendMessage();
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                sendCustomKeyboardMessage(chatId);
            } else if (messageText.equals("Linux")) {
                sendTextMessage(chatId, "You are gay");
            } else if(messageText.equals("Windows")){
                sendTextMessage(chatId, "Incorrect answer! Choose another one please)");
            }
        }
    }
    private void sendCustomKeyboardMessage(long message) {
        sendMessage
                .setChatId(message)
                .setText("Choose the best option:");

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        row1.add("Windows");
        row1.add("Linux");

        keyboardMarkup.getKeyboard().add(row1);

        sendMessage.setReplyMarkup(keyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
        }
    }

    private void sendTextMessage(long chatId, String text) {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {}
    }

    @Override
    public String getBotUsername() {
        return "java_language_bot";
    }

    @Override
    public String getBotToken() {
        return "6344310347:AAH1QOJbo3QYMTU_gMlx4ismJUE88GhxUPE";
    }
}
