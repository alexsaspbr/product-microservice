package tech.ada.product_microservice.infrastructure.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.Optional;

@UtilityClass
public class SortUtils {

    public static Optional<Sort> createSort(String sort) {
        if(Objects.nonNull(sort)) {
            String[] sortSplit = sort.split(",");
            return Optional.of(Sort.by(Sort.Direction.fromString(sortSplit[1]),
                                sortSplit[0]));
        }

        return Optional.empty();

    }

}
