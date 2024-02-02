package Dictionary;

public class WordProperties {

        public String meaning;
        public String word;
        public int frequency;

        public WordProperties(String meaning, String word, int frequency) {
            this.meaning = meaning;
            this.word = word;
            this.frequency = frequency; // Initialize frequency to 0
        }

        public String getMeaning() {
            return meaning;
        }

        public int getFrequency() {
            return frequency;
        }

        public void incrementFrequency() {
            frequency++;
        }
    }
