package com.example.demo.boxes;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BoxService {
    private final BoxRepo repo;

    public int maxHeight() {
        List<Box> boxes = repo.getSorted();

        int[] heights = new int[boxes.size()];

        for (int i = boxes.size() - 1; i >= 0; i--) {
            int count = 0;
            Box box = boxes.get(i);
            for (int j = i + 1; j < heights.length; j++) {
                Box another = boxes.get(j);
                if (box.getX() >= another.getX() && box.getY() >= another.getY() && heights[j] > count) {
                    count = heights[j];
                }
            }
            heights[i] = count + 1;
        }

        /*List<Box> list = List.of(
                new Box(46, 15),    //heights = 3
                new Box(21, 47),    //heights = 5
                new Box(17, 32),    //heights = 4
                new Box(15, 12),    //heights = 2
                new Box(14, 14),    //heights = 2
                new Box(12, 31),    //heights = 3
                new Box(12, 31)     //heights = 2
                new Box(5, 7),      //heights = 1
        );*/

        return Arrays.stream(heights).max().orElseThrow(() -> new RuntimeException("Error"));
    }

    public List<Box> findMaxHeightPyramidOpt() {
        List<Box> boxes = repo.getSorted();
        int[] heights = new int[boxes.size()];

        List<Box> result = new ArrayList<>();
        int current = 0;

        for(int i = boxes.size() - 1; i >= 0; i--) {
            Box box = boxes.get(i);
            int count = 0;
            for (int j = i + 1; j < boxes.size(); j++) {
                Box currentBox = boxes.get(j);
                if (box.getX() >= currentBox.getX() && box.getY() >= currentBox.getY() && heights[j] > count) {
                    count = heights[j];
                }
            }
            heights[i] = count + 1;
            if (heights[i] > current) {
                current = heights[i];
                result.add(box);
            }
        }
        return result;
    }

    public List<Box> findMaxHeightPyramid() {
        List<Box> boxes = repo.getSorted();
        int[] heights = new int[boxes.size()];

        for(int i = boxes.size() - 1; i >= 0; i--) {
            Box box = boxes.get(i);
            int count = 0;
            for (int j = i + 1; j < boxes.size(); j++) {
                Box currentBox = boxes.get(j);
                if (box.getX() >= currentBox.getX() && box.getY() >= currentBox.getY() && heights[j] > count) {
                    count = heights[j];
                }
            }
            heights[i] = count + 1;
        }
        List<Box> result = new ArrayList<>();

        int[] arr = Arrays.copyOf(heights, heights.length);
        int index = findIndexByValueFromBottom(heights, findMax(arr));
        result.add(boxes.get(index));
        while(arr.length > 0) {
            arr = Arrays.copyOfRange(heights, index + 1, heights.length);
            index = findIndexByValueFromBottom(heights, findMax(arr));
            if (index >= 0) {
                result.add(boxes.get(index));
            }
        }
        return revers(result);
    }

    private int findIndexByValueFromBottom(int arr[], int val) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }


    private int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private List<Box> revers(List<Box> list) {
        List<Box> res = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            res.add(list.get(i));
        }
        return res;
    }


    /**
     * Не работает короче =)
     * @return фигню
     */
    @Deprecated
    public int countHeight() {
        List<Box> list = repo.findAll();
        /*List<Box> list = List.of(
                new Box(5, 7),      //6
                new Box(12, 31),    //2    //2
                new Box(14, 14),    //3
                new Box(17, 32),    //1
                new Box(21, 47),    //0
                new Box(46, 15),    //0
                new Box(15, 12),    //3
                new Box(12, 31)     //----             //Итог: 6 -> {5, 7}, {14, 14}, {12, 31}, {12, 31}, {17, 32}, {21, 47}
        );*/
        Set<Integer> notUniques = new HashSet<>();
        Map<Integer, Integer> countToCost = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (notUniques.contains(i)) {
                continue;
            }
            var box = list.get(i);
            int cost = 1;
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
                if (i == j || notUniques.contains(j)) {
                    continue;
                }
                if (box.getX().equals(list.get(j).getX()) && box.getY().equals(list.get(j).getY())) {
                    cost++;
                    notUniques.add(j);
                } else if (box.getX() < list.get(j).getX() && box.getY() < list.get(j).getY()) {
                    count++;
                }
            }
            if (!countToCost.containsKey(count)) {
                countToCost.put(count, cost);
            } else {
                int current = countToCost.get(count);
                if (current < cost) {
                    countToCost.put(count, cost);
                }
            }
        }
        int sum = 0;
        for (var i : countToCost.values()) {
            sum += i;
        }
        return sum;
    }

    @PostConstruct
    private void logs() {
        List<Box> list = List.of(
                new Box(5, 7),
                new Box(12, 31),
                new Box(14, 14),
                new Box(17, 32),
                new Box(21, 47),
                new Box(46, 15),
                new Box(15, 12),
                new Box(12, 31)
        );
        repo.saveAll(list);
        //ystem.out.println("-------------------->" + countHeight() + "<---------------------");
        //System.out.println("-------------------->" + maxHeight() + "<---------------------");

    }

    /*
    n - кол-во коробок
    m - кол-во постановок коробок друг на друга1
    l - максимальная высота

    Сначала исключить все повторяющиеся коробки. Создать мапу, куда записать количество повторений для одной коробки, используя коробу в качестве ключа.

    n = 0
    {} - n = 0, m = 0, l = 0

    n = 1
    {{1, 2}} - n = 1 m = 0, l = 1

    n = 2
    {{1, 2}, {2, 1}} - m = 0, l = 1     ->  0 - 0, 1 - 0    sum = 0     max = 0     count = 0   l = 1
    {{1, 2}, {2, 3}} - m = 1, l = 2     ->  0 - 1, 1 - 0    sum = 1     max = 1     count = 1   l = 2
    {{1, 2}, {1, 2}} - m = 2, l = 2     ->  0 - 1, 1 - 1    sum = 2     max = 1     count = 2   l = 2    ===    {{1, 2}}    l = 1 + (1 - кол-во повторов)

    **  n = 3

    {{4, 2}, {2, 4}, {1, 5}}    ->    0, 0, 0     sum = 0     max = 0     count = 0   l = 1

    {{4, 2}, {2, 4}, {3, 4}}    ->    0, 1, 0     sum = 1     max = 1     count = 1   l = 2
    {{1, 2}, {3, 2}, {2, 3}}    ->    2, 0, 0     sum = 2     max = 2     count = 1   l = 2

    {{1, 2}, {2, 3}, {3, 4}}    ->    2, 1, 0     sum = 3     max = 2     count = 2    l = 3
    {{1, 2}, {1, 2}, {2, 3}}    ->    2, 2, 0     sum = 4     max = 2     count = 2    l = 3    ===    {{1, 2}, {2, 3}}     ->    1(2), 0(1)
    {{1, 2}, {1, 2}, {1, 2}}    ->    2, 2, 2     sum = 6     max = 2     count = 3    l = 3    ===    {{1, 2}}

    **  n = 4

    {{3, 7}, {5, 4}, {4, 6}, {6, 3}}    ->    0, 0, 0, 0    sum = 0    max = 0    count = 0    uniq = 1    l = 1

    {{3, 5}, {5, 4}, {4, 6}, {6, 3}}    ->    1, 0, 0, 0    sum = 1    max = 1    count = 1    uniq = 2    l = 2
    {{3, 5}, {2, 6}, {4, 6}, {6, 3}}    ->    1, 1, 0, 0    sum = 2    max = 1    count = 2    uniq = 2    l = 2
    {{4, 3}, {5, 4}, {4, 6}, {6, 3}}    ->    3(1), 0(1), 0(1), 0(1)    sum = 3    max = 3    count = 1    uniq = 2    l = 2

    {{5, 3}, {5, 3}, {3, 5}, {3, 5}}    ->    1, 1, 1, 1    sum = 4    max = 1    count = 4    uniq = 1    l = 2    ===    {{5, 3}, {3, 5}}    ->    0(2), 0(2)    l = max 0 ({3, 5}, {5, 3})

    {{3, 5}, {2, 4}, {4, 6}, {6, 3}}    ->    1, 2, 0, 0    sum = 3    max = 2    count = 2    uniq = 3    l = 3
    {{1, 1}, {5, 4}, {6, 6}, {7, 3}}    ->    3, 1, 0, 0    sum = 4    max = 3    count = 2    uniq = 3    l = 3
    {{5, 4}, {5, 4}, {6, 6}, {7, 3}}    ->    2, 2, 0, 0    sum = 4    max = 2    count = 2    uniq = 2    l = 3
    {{1, 1}, {5, 4}, {6, 6}, {4, 5}}    ->    3, 1, 0, 1    sum = 5    max = 3    count = 3    uniq = 3    l = 3
    {{5, 4}, {5, 4}, {7, 7}, {4, 6}}    ->    2, 2, 0, 1    sum = 5    max = 2    count = 3    uniq = 3    l = 3
    {{1, 2}, {2, 2}, {2, 3}, {3, 2}}    ->    3, 2, 0, 0    sum = 5    max = 3    count = 2    uniq = 3    l = 3
    {{1, 2}, {3, 3}, {3, 3}, {4, 2}}    ->    3, 1, 1, 0    sum = 5    max = 3    count = 3    uniq = 3    l = 3
    {{2, 2}, {2, 2}, {2, 2}, {1, 3}}    ->    2, 2, 2, 0    sum = 6    max = 2    count = 3    uniq = 2    l = 3

    {{1, 2}, {2, 2}, {2, 3}, {3, 3}}    ->    3, 2, 1, 0    sum = 6    max = 3    count = 3    uniq = 4    l = 4
    {{1, 2}, {3, 3}, {3, 3}, {2, 3}}    ->    3, 1, 1, 2    sum = 7    max = 3    count = 4    uniq = 3    l = 4
    {{1, 2}, {2, 2}, {2, 2}, {3, 3}}    ->    3, 2, 2, 0    sum = 7    max = 3    count = 3    uniq = 3    l = 4
    {{5, 5}, {5, 5}, {5, 5}, {5, 5}}    ->    3, 3, 3, 3    sum = 12   max = 3    count = 4    uniq = 1    l = 4


    {{60, 20}, {50, 10}, {40, 40}, {30, 30}, {5, 5}}    ->    0, 1, 0, 1, 4 (повторов нет, уникальных 3) -> высота = 3

    * */
}
