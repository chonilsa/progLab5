package file;

import com.opencsv.CSVReader;
import exception.UniqueIdException;
import object.*;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * The type Csv parsing.
 */
public class CsvParsing {
    /**
     * The constant filePath.
     */
    public static String filePath;

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public static String getFilePath() {
        return filePath;
    }

    /**
     * Parse to collection array list.
     *
     * @param text the text
     * @return the array list
     */
    public ArrayList<SpaceMarine> parseToCollection(String text) {
        CSVReader csvReader = new CSVReader(new StringReader(text));
        ArrayList<SpaceMarine> arrayList = new ArrayList<>();
        
        try {
            List<String[]> allData = csvReader.readAll();
            HashSet<Long> hashSet = new HashSet<>();
            long id;
            String name;
            Integer x;
            float y;
            Date date;
            float health;
            AstartesCategory category;
            Weapon weaponType;
            MeleeWeapon meleeWeapon;
            String chapterName;
            long marinesCount;
            for (String[] row : allData) {
                try {
                    id = Long.parseLong(row[0]);
                    for (Long i: hashSet) {
                        if (i == id) {
                            throw new UniqueIdException();
                        }
                    }
                    hashSet.add(id);
                    name = row[1];
                    x = Integer.valueOf(row[2]);
                    y = Float.parseFloat(row[3]);
                    date = Date.from(Instant.ofEpochMilli(Long.parseLong(row[4])));
                    health = Float.parseFloat(row[5]);
                    category = AstartesCategory.valueOf(row[6]);
                    weaponType = Weapon.valueOf(row[7]);
                    meleeWeapon = MeleeWeapon.valueOf(row[8]);
                    chapterName = row[9];
                    marinesCount = Long.parseLong(row[10]);
                    arrayList.add(new SpaceMarine(id, name, new Coordinates(x, y),
                            date, health, category, weaponType,
                            meleeWeapon, new Chapter(chapterName, marinesCount)));
                }catch (UniqueIdException ex){
                    System.err.println("Нарушена уникальность id, проверьте значения этих полей в файле");
                } catch(RuntimeException ex){
                    System.err.println("В файле есть невалидные значение, поля записанные в строчках с такими значениями не будут учтены");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * Parse to csv string.
     *
     * @param arrayList the array list
     * @return the string
     */
    public String parseToCsv(ArrayList<SpaceMarine> arrayList) {
        StringBuilder sb = new StringBuilder();
        for (SpaceMarine sp : arrayList) {
            sb.append(sp.getId() + "," + sp.getName() + "," + sp.getCoordinates().getX() + "," + sp.getCoordinates().getY() + "," + sp.getCreationDate() + "," +
                    sp.getHealth() + "," + sp.getCategory().name() + "," + sp.getWeaponType().name() + "," + sp.getMeleeWeapon().name() + "," +
                    sp.getChapter().getName() + "," + sp.getChapter().getMarinesCount() + "\n");
        }
        return sb.toString();
    }
}
//                    long id, String name, Integer x, float y, String date, float health, String category, String weaponType,
//                    String meleeWeapon, String name, long marinesCount
//                long id, String name, Coordinates coordinates, Date date, float health, AstartesCategory category, Weapon weaponType,
//                MeleeWeapon meleeWeapon, Chapter chapter