
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Interface {
    private static BSTDictionary dictionary;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Interface <inputFile>");
            return;
        }

        String inputFile = args[0];
        dictionary = new BSTDictionary();

        try {
            readInputFile(inputFile);
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
            return;
        }

        StringReader keyboard = new StringReader();
        String command;
        do {
            command = keyboard.read("Enter next command: ");
            processCommand(command);
        } while (!command.equals("exit"));
    }

    private static void readInputFile(String inputFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        String label = null;
        String typeAndData;

        while ((line = reader.readLine()) != null) {
            if (label == null) {
                label = line.trim();
            } else {
                typeAndData = line.trim();
                processRecord(label, typeAndData);
                label = null;
            }
        }

        reader.close();
    }

    private static void processRecord(String label, String typeAndData) {
        char type = typeAndData.charAt(0);
        String data = typeAndData.substring(1).trim();

        switch (type) {
            case '-':
                Record definitionRecord = new Record(new Key(label.toLowerCase(), 3), data);
                try {
                    dictionary.put(definitionRecord );
                } catch (DictionaryException e) {
                    System.out.println("Error storing record: " + e.getMessage());
                }
                System.out.println("\n\nLabel: " + label);
                System.out.println("Record Type is a: " + 3);
                System.out.println("Data: " + data +"\n\n");
                // Handle sound file
                System.out.println("Sound file: " + data);
                break;
            case '+':
                Record musicRecord = new Record(new Key(label.toLowerCase(), 4), data);
                try {
                    dictionary.put(musicRecord );
                } catch (DictionaryException e) {
                    System.out.println("Error storing record: " + e.getMessage());
                }
                System.out.println("\n\nLabel: " + label);
                System.out.println("Record Type is a: " + 4);
                System.out.println("Data: " + data +"\n\n");
                // Handle music file
                System.out.println("Music file: " + data);
                break;
            case '*':
                Record languageRecord = new Record(new Key(label.toLowerCase(), 5), data);
                try {
                    dictionary.put(languageRecord);
                } catch (DictionaryException e) {
                    System.out.println("Error storing record: " + e.getMessage());
                }
                System.out.println("\n\nLabel: " + label);
                System.out.println("Record Type is a: " + 5);
                System.out.println("Data: " + data +"\n\n");
                // Handle voice file
                System.out.println("Voice file: " + data);
                break;
            case '/':
                Record frecord = new Record(new Key(label.toLowerCase(), 2), data);
                try {
                    dictionary.put(frecord);
                } catch (DictionaryException e) {
                    System.out.println("Error storing record: " + e.getMessage());
                }
                System.out.println("\n\nLabel: " + label);
                System.out.println("Record Type is a: " + 2);
                System.out.println("Data: " + data +"\n\n");
                // Handle translation to French
                System.out.println("French translation: " + data);
                break;
            default:
                int recordType;
                if (typeAndData.contains(".")) {
                    String extension = data.substring(data.lastIndexOf(".") + 1);
                    switch (extension) {
                        case "gif":
                            recordType = 7;
                            data =  typeAndData;
                            break;
                        case "jpg":
                            recordType = 6;
                            data =  typeAndData;
                            break;
                        case "html":
                            recordType = 8;
                            data =  typeAndData;
                            break;
                        default:
                            data =  typeAndData;
                            recordType = 1;
                            break;
                    }
                } else {
                    recordType = 1;
                }
                Record record = new Record(new Key(label.toLowerCase(), recordType), data);
                System.out.println("\n\nLabel: " + label);
                System.out.println("Record Type is a: " + recordType);
                System.out.println("Data: " + data +"\n\n");
                try {
                    dictionary.put(record);
                } catch (DictionaryException e) {
                    System.out.println("Error storing record: " + e.getMessage());
                }
                break;
        }
    }

    private static void processCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        

        String word = "";
        if (parts.length == 2 && !Objects.equals(parts[0], "first") && !Objects.equals(parts[0], "last")) {
            word = parts[1];
        }

            switch (parts[0]) {
                case "define":

                    defineWord(word);
                    // Process define command
                    break;
                case "translate":
                    translateWord(word);
                    // Process translate command
                    break;
                case "sound":
                    playSound(word);
                    // Process sound command
                    break;
                case "play":
                    // Process play command
                    playMusic(word);
                    break;
                case "say":
                    // Process say command
                    playVoice(word);
                    break;
                case "show":
                    // Process show command
                    showImage(word);
                    break;
                case "animate":
                    showAnimatedImage(word);
                    break;
                case "browse":
                    browseWebpage(word);
                    break;
                case "delete":
                    deleteRecord(parts);
                    break;
                case "add":
                    addRecord(parts);
                    break;
                case "list":
                    listPrefix(word);
                    break;
                case "first":
                    printFirstRecord();
                    break;
                case "last":
                    printLastRecord();
                    break;
                case "exit":
                    // Terminate the program
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }

    private static void defineWord(String word) {
        Key key = new Key(word.toLowerCase(), 1);
        Record record = dictionary.get(key);

        if (record != null) {
            System.out.println(record.getDataItem());
        } else {
            System.out.println("The word " + word + " is not in the ordered dictionary.");
        }
    }
    private static void translateWord(String word) {
        Key key = new Key(word.toLowerCase(), 2);
        Record record = dictionary.get(key);

        if (record != null) {
            System.out.println(record.getDataItem());
        } else {
            System.out.println("There is no definition for the word " + word);
        }
    }
    private static void playSound(String word) {
        Key key = new Key(word.toLowerCase(), 3);
        Record record = dictionary.get(key);

        if (record != null) {
            String soundFileName = record.getDataItem();
            SoundPlayer soundPlayer = new SoundPlayer();
            try {
                soundPlayer.play(soundFileName);
            }
            catch(Exception e){
                System.out.println("Exception : " + e.getMessage());
            }
        } else {
            System.out.println("There is no sound file for " + word);
        }
    }
    private static void playMusic(String word) {
        Key key = new Key(word.toLowerCase(), 4);
        Record record = dictionary.get(key);

        if (record != null) {
            String musicFileName = record.getDataItem();
            SoundPlayer soundPlayer = new SoundPlayer();
            try {
                soundPlayer.play(musicFileName);
            }
            catch(Exception e){
                System.out.println("Exception occurred for playing music : " + e.getMessage());
            }

        } else {
            System.out.println("There is no music file for " + word);
        }
    }

    private static void playVoice(String word) {
        Key key = new Key(word.toLowerCase(), 5);
        Record record = dictionary.get(key);

        if (record != null) {
            String voiceFileName = record.getDataItem();
            try {
                SoundPlayer player = new SoundPlayer();
                player.play(voiceFileName);
            }
            catch (MultimediaException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There is no voice file for " + word);
        }
    }

    private static void showImage(String word) {
        Key key = new Key(word.toLowerCase(), 6);
        Record record = dictionary.get(key);

        if (record != null) {
            String imageFileName = record.getDataItem();
            try {
                PictureViewer viewer = new PictureViewer();
                viewer.show(imageFileName);
            }
            catch (MultimediaException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("There is no image file for " + word);
        }
    }
    private static void showAnimatedImage(String word) {
        Key key = new Key(word.toLowerCase(), 7);
        Record record = dictionary.get(key);

        if (record != null) {
            String animatedImageFileName = record.getDataItem();
            try {
                PictureViewer viewer = new PictureViewer();
                viewer.show(animatedImageFileName);
            }
            catch (MultimediaException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There is no animated image file for " + word);
        }
    }

    private static void browseWebpage(String word) {
        Key key = new Key(word.toLowerCase(), 8);
        Record record = dictionary.get(key);

        if (record != null) {
            String webpageUrl = record.getDataItem();
            ShowHTML showObject = new ShowHTML();
            showObject.show(webpageUrl);
        } else {
            System.out.println("There is no webpage called " + word);
        }
    }
    private static void deleteRecord(String[] parts) {
        if (parts.length != 3) {
            System.out.println("Invalid command format for delete.");
            return;
        }

        String word = parts[1];
        int key = Integer.parseInt(parts[2]);
        Key deleteKey = new Key(word.toLowerCase(), key);

        try {
            dictionary.remove(deleteKey);
            System.out.println("Record with key (" + word + "," + key + ") deleted.");
        } catch (DictionaryException e) {
            System.out.println("No record in the ordered dictionary has key (" + word + "," + key + ")");
        }
    }

    private static void addRecord(String[] parts) {
        if (parts.length != 4) {
            System.out.println("Invalid command format for add.");
            return;
        }

        String word = parts[1];
        int type = Integer.parseInt(parts[2]);
        String data = parts[3];
        Key addKey = new Key(word.toLowerCase(), type);
        Record newRecord = new Record(addKey, data);

        try {
            dictionary.put(newRecord);
            System.out.println("Record added successfully.");
        } catch (DictionaryException e) {
            System.out.println("A record with the given key (" + word + "," + type + ") is already in the ordered dictionary.");
        }
    }

    private static void listPrefix(String prefix) {
        List<String> labels = dictionary.getRecordsWithPrefix(prefix);

        if (labels.isEmpty()) {
            System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
        } else {
            for (String label : labels) {
                System.out.println(label);
            }
        }
    }

    private static void printFirstRecord() {
        Record firstRecord = dictionary.smallest();
        if (firstRecord != null) {
            Key key = firstRecord.getKey();
            System.out.println(key.getLabel() + ", " + key.getType() + ", " + firstRecord.getDataItem());
        }
    }

    private static void printLastRecord() {
        Record lastRecord = dictionary.largest();
        if (lastRecord != null) {
            Key key = lastRecord.getKey();
            System.out.println(key.getLabel() + ", " + key.getType() + ", " + lastRecord.getDataItem());
        }
    }





}
