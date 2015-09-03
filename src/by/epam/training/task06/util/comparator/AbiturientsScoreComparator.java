package by.epam.training.task06.util.comparator;

import by.epam.training.task06.entity.User;
import by.epam.training.task06.logic.LogicHelp;

import java.util.Comparator;

/**
 * <p>
 *     Provides comparator to compare abiturients points sum
 * </p>
 */
public class AbiturientsScoreComparator implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        int result1 = LogicHelp.calculateCertificatesSum(user1);
        int result2 = LogicHelp.calculateCertificatesSum(user2);

        if (result1 < result2) {      //from highest value to lower
            return 1;
        } else if (result1 > result2) {
            return -1;
        } else {
            return 0;
        }
    }
}
