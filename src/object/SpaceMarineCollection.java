package object;

import exception.ValidValuesRangeException;
import file.CsvParsing;
import file.FileManager;

import java.text.SimpleDateFormat;
import java.util.*;

public class SpaceMarineCollection {
    PriorityQueue<SpaceMarine> priorityQueue;

    Date collectionInitialization = new Date();
    String date;

    public SpaceMarineCollection() {
        this.priorityQueue = new PriorityQueue<>();
        date = new SimpleDateFormat(SpaceMarine.getPattern()).format(collectionInitialization);
    }

    public PriorityQueue<SpaceMarine> getPriorityQueue() {
        return priorityQueue;
    }

    public void info() {
        System.out.println("Коллекция " + priorityQueue.getClass().getSimpleName());
        System.out.println("Тип элементов коллекции: " + SpaceMarine.class.getSimpleName());
        date = new SimpleDateFormat(SpaceMarine.getPattern()).format(collectionInitialization);
        System.out.println("Время инициализации коллекции: " + date);
        System.out.println("Количество элементов в коллекции: " + priorityQueue.size());
    }

    public void show() {
        if (priorityQueue.size() == 0) {
            System.out.println("Коллекция пуста.");
        } else {
            for (SpaceMarine item : priorityQueue) {
                System.out.println(item.toString());
            }
        }
    }

    public void clear() {
        this.priorityQueue.clear();
    }

    public void removeById(long id) {
//        this.priorityQueue.removeIf(x -> x.getId() == id);
        List<SpaceMarine> list = new ArrayList<>(priorityQueue.size());
        PriorityQueue<SpaceMarine> priorityQueue2 = new PriorityQueue<>(priorityQueue);
        while (!priorityQueue2.isEmpty()) {
            list.add(priorityQueue2.poll());
        }
        for (SpaceMarine spm : list) {
            if (spm.getId() == id) {
                priorityQueue.remove(spm);
            }
        }
    }

    public void head() {
        try {
            System.out.println(priorityQueue.peek().toString());
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Ссылка на элемент за пределами коллекции");
        } catch (NullPointerException ex) {
            System.err.println("Аргумент ссылается на null");
        }

    }

    public void save() {
        CsvParsing csvParsing = new CsvParsing();
        FileManager fileManager = new FileManager();
//        SpaceMarine[] spaceMarines = new SpaceMarine[priorityQueue.size()];
//        spaceMarines = (SpaceMarine[]) priorityQueue.toArray();
//        ArrayList<SpaceMarine> arrayList = new ArrayList<>();
//        arrayList = priorityQueue.toArray();
//        arrayList = Arrays.asList(priorityQueue);
        List<SpaceMarine> list = new ArrayList<SpaceMarine>(priorityQueue);
        ArrayList<SpaceMarine> arrayList = new ArrayList<SpaceMarine>(list);
        String str = csvParsing.parseToCsv(arrayList);
        fileManager.writeToFile(str);
    }

    public void filterLessThanCategory(AstartesCategory astartesCategory) {
        int thatFieldOrdinal = astartesCategory.getCategoryOrdinal();
        StringBuilder sb = new StringBuilder();
        for (SpaceMarine spm : priorityQueue) {
            if (spm.getCategoryOrdinal() < thatFieldOrdinal) {
                sb.append(spm.toString());
            }
        }
        System.out.println(sb.toString());
    }

    public void printFieldDescendingCategory() {
        CategoryComparator categoryComparator = new CategoryComparator();
//        Object arr = priorityQueue.toArray();
//        ArrayList<SpaceMarine> arrayList = new ArrayList<>();
//        arrayList.addAll(arr);
//        Collections.sort(priorityQueue.toArray(), categoryComparator);
        List<SpaceMarine> list = new ArrayList<>(priorityQueue.size());
        PriorityQueue<SpaceMarine> priorityQueue2 = new PriorityQueue<>(priorityQueue);
        while (!priorityQueue2.isEmpty()) {
            list.add(priorityQueue2.poll());
        }
        Collections.sort(list, categoryComparator);
        for (SpaceMarine spm : list) {
            System.out.print(spm.getCategory().toString() + " ");
        }
        System.out.println();
    }

    public void printUniqueChapter() {
        HashSet<String> set = new HashSet<>();
        PriorityQueue<SpaceMarine> priorityQueue2 = new PriorityQueue<>(priorityQueue);
        while (!priorityQueue2.isEmpty()) {
            Chapter chapter2 = priorityQueue2.poll().getChapter();
            set.add(chapter2.getName() + " " + chapter2.getMarinesCount());
        }
        for (String s : set) {
            System.out.println("(chapter_name, chapter_marines_count) = (" + s.split(" ")[0] + ", " + s.split(" ")[1] + ")");
        }
    }

    public void add(SpaceMarine spaceMarine) {
        priorityQueue.add(spaceMarine);
    }

    public String getFieldNames() {
        return "Список всех полей:\n" +
                "(String) name,\n" +
                "(Integer) coordinate_x,\n" +
                "(float) coordinate_y,\n" +
                "(float) health,\n" +
                "(AstartesCategory) category: " + Arrays.toString(AstartesCategory.values()) + ",\n" +
                "(Weapon) weapon_type: " + Arrays.toString(Weapon.values()) + ",\n" +
                "(MeleeWeapon) melee_weapon: " + Arrays.toString(MeleeWeapon.values()) + ",\n" +
                "(String) chapter_name, \n" +
                "(long) chapter_marines_count";
    }

    public boolean addIfMin(SpaceMarine spaceMarine) {
        ChapterComparator chapterComparator = new ChapterComparator();
        List<SpaceMarine> list = new ArrayList<>(priorityQueue.size());
        PriorityQueue<SpaceMarine> priorityQueue2 = new PriorityQueue<>(priorityQueue);
        while (!priorityQueue2.isEmpty()) {
            list.add(priorityQueue2.poll());
        }
        Collections.sort(list, chapterComparator);
        try {
            if (list.get(0).getChapter().getMarinesCount() > spaceMarine.getChapter().getMarinesCount()) {
                priorityQueue.add(spaceMarine);
                return true;
            } else {
                return false;
            }
        } catch (IndexOutOfBoundsException ex) {
            System.err.println("Коллекция не содержит объектов");
        }
        return false;
    }

    public boolean containsKey(long id) {
        for (SpaceMarine spm : priorityQueue) {
            if (spm.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void update(Integer id, String field, String value) {
        try {
            List<SpaceMarine> list = new ArrayList<>(priorityQueue.size());
            PriorityQueue<SpaceMarine> priorityQueue2 = new PriorityQueue<>(priorityQueue);
            while (!priorityQueue2.isEmpty()) {
                list.add(priorityQueue2.poll());
            }
            switch (field) {
                case "name": {
                    if (value.equals("")) throw new NullPointerException();
                    for (SpaceMarine spm : list) {
                        if (spm.getId() == id) {
                            priorityQueue.remove(spm);
                            spm.setName(value);
                            priorityQueue.add(spm);
                            System.out.println("Значение поля было изменено");
                            break;
                        }
                    }
                    break;
                }
                case "coordinate_x": {
                    for (SpaceMarine spm : list) {
                        if (spm.getId() == id) {
                            priorityQueue.remove(spm);
                            spm.setCoordinateX(Integer.parseInt(value));
                            priorityQueue.add(spm);
                            System.out.println("Значение поля было изменено");
                            break;
                        }
                    }
                    break;
                }
                case "coordinate_y": {
                    for (SpaceMarine spm : list) {
                        if (spm.getId() == id) {
                            priorityQueue.remove(spm);
                            spm.setCoordinateY(Integer.parseInt(value));
                            priorityQueue.add(spm);
                            System.out.println("Значение поля было изменено");
                            break;
                        }
                    }
                    break;
                }
                case "health": {
                    for (SpaceMarine spm : list) {
                        if (Float.parseFloat(value) < 0) {
                            throw new ValidValuesRangeException();
                        }
                        if (spm.getId() == id) {
                            priorityQueue.remove(spm);
                            spm.setHealth(Float.parseFloat(value));
                            priorityQueue.add(spm);
                            System.out.println("Значение поля было изменено");
                            break;
                        }
                    }
                    break;
                }
                case "category": {
                    for (SpaceMarine spm : list) {
                        if (spm.getId() == id) {
                            priorityQueue.remove(spm);
                            spm.setCategory(AstartesCategory.valueOf(value.toUpperCase(Locale.ROOT)));
                            priorityQueue.add(spm);
                            System.out.println("Значение поля было изменено");
                            break;
                        }
                    }
                    break;
                }
                case "weapon_type": {
                    for (SpaceMarine spm : list) {
                        if (spm.getId() == id) {
                            priorityQueue.remove(spm);
                            spm.setWeaponType(Weapon.valueOf(value.toUpperCase(Locale.ROOT)));
                            priorityQueue.add(spm);
                            System.out.println("Значение поля было изменено");
                            break;
                        }
                    }
                    break;
                }
                case "melee_weapon": {
                    for (SpaceMarine spm : list) {
                        if (spm.getId() == id) {
                            priorityQueue.remove(spm);
                            spm.setMeleeWeapon(MeleeWeapon.valueOf(value.toUpperCase(Locale.ROOT)));
                            priorityQueue.add(spm);
                            System.out.println("Значение поля было изменено");
                            break;
                        }
                    }
                    break;
                }
                case "chapter_name": {
                    if (value.equals("")) {
                        throw new ValidValuesRangeException();
                    } else {
                        for (SpaceMarine spm : list) {
                            if (spm.getId() == id) {
                                priorityQueue.remove(spm);
                                spm.setChapterName(value);
                                priorityQueue.add(spm);
                                System.out.println("Значение поля было изменено");
                                break;
                            }
                        }
                    }
                    break;
                }
                case "chapter_marines_count": {
                    if (Long.parseLong(value) <= 0 || Long.parseLong(value) > 1000) {
                        throw new ValidValuesRangeException();
                    } else {
                        for (SpaceMarine spm : list) {
                            if (spm.getId() == id) {
                                priorityQueue.remove(spm);
                                spm.setChapterMarinesCount(Long.parseLong(value));
                                priorityQueue.add(spm);
                                System.out.println("Значение поля было изменено");
                                break;
                            }
                        }
                    }
                    break;
                }
                case "stop": {
                    break;
                }
                default: {
                    System.out.println("Поле не распознано");
                    break;
                }
            }
        } catch (ClassCastException ex) {
            System.err.println("Указано недопустимое значение для данного поля");
        } catch (IllegalArgumentException ex) {
            System.err.println("Значение аргумента не соответствует ожидаемому");
        } catch (NullPointerException ex) {
            System.err.println("Невозможно записать null в данное поле");
        } catch (ValidValuesRangeException ex) {
            System.err.println("Значение аргумента должно находится в заданных пределах");
        }
    }
}
