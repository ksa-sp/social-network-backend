package ru.skillbox.socialnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skillbox.socialnet.entity.other.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query(nativeQuery = true, value = """
        select
            *
        from weather
        where city = :city
        order by date desc
        limit 1
        """)
    Weather findLastByCity(@Param("city") String city);
}
