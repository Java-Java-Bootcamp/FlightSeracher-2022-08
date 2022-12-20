package ru.otus.searcher.applicationRunner;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StaticDataListenerRunner {

    private final CityLoadingServiceRunner cityLoadingServiceRunner;
    private final CountryLoadingServiceRunner countryLoadingServiceRunner;
    private final AirportLoadingServiceRunner airportLoadingServiceRunner;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        countryLoadingServiceRunner.load();
        cityLoadingServiceRunner.load();
        airportLoadingServiceRunner.load();
    }
}
