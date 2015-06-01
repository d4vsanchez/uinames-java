package name.davidsanchez.uiname;

import java.io.File;
import java.util.*;

/**
 * @author David Sanchez
 * @version 1.0.0
 * @since 2015-05-31
 */
public class UIName implements UIRequestResponse {
    private Map<String, String> parameters;
    private final String[] GENDER = {"male", "female"};
    private UINameRequest request;

    public UIName() {
        parameters = setDefaultParameters();
        request = new UINameRequest(new File("./cache"), this);
    }

    private Map<String, String> setDefaultParameters() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("amount", "1");

        return parameters;
    }

    private String getRandomElement(String[] array) {
        Random random = new Random();
        return array[(int)Math.floor(random.nextInt(array.length))];
    }

    /**
     * Set a country where the generator will pick the names;
     *
     * @param country The country where the user wants to generate the names.
     * @return This class with the country added to the parameters.
     */
    public UIName setCountry(String country) {
        parameters.put("country", country.toLowerCase());

        return this;
    }

    /**
     * Set an amount of names to be returned
     *
     * @param amount The amount of names the user wants to generate.
     * @return This class with the amount added to the parameters.
     */
    public UIName setAmount(int amount) {
        parameters.put("amount", String.valueOf(amount));

        return this;
    }

    /**
     * Set a gender for names to be returned
     *
     * @param gender The gender of the names the user wants to generate.
     * @return This class with the amount added to the parameters.
     */
    public UIName setGender(String gender) {
        parameters.put("gender", gender.toLowerCase());

        return this;
    }

    /**
     * Generates the names based on the parameters passed by the user.
     *
     * @param uiNameResponse A callback to be executed when the values has been received
     */
    public void generateNames(UINameResponse uiNameResponse) {
        request.getData(parameters, uiNameResponse);
    }

    /**
     * Callback to be executed after receiving object from the request.
     * This converts the raw JSON into its specific class.
     *
     * @param rawValues The JSON raw values.
     * @param uiNameResponse The callback to be executed after parsing the data.
     */
    @Override
    public void receiveData(List<Map<String, Object>> rawValues, UINameResponse uiNameResponse) {
        Person[] people = new Person[rawValues.size()];

        for (int i = 0; i < rawValues.size(); i++) {
            people[i] = new Person(rawValues.get(i));
        }

        uiNameResponse.onNamesReceived(people);
    }
}