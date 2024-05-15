//STATISTICS FAIL :(


//    private static void showStatistics() {
//        Map<String, Double> averageScores = new HashMap<>();
//        Map<String, Integer> quizCounts = new HashMap<>();
//
//        HashMap<Object, Object> history;
//        for (Map.Entry<Object, Object> entry : history.entrySet()) {
//            String username = entry.getKey();
//            List<String> results = entry.getValue();
//
//            int totalScore = 0;
//            for (String result : results) {
//                String[] parts = result.split("-");
//                totalScore += Integer.parseInt(parts[2]);
//            }
//
//            double averageScore = (double) totalScore / results.size();
//            averageScores.put(username, averageScore);
//            quizCounts.put(username, results.size());
//        }
//
//        System.out.println("\nUsers with the most quizzes taken:");
//        List<Map.Entry<String, Integer>> quizCountList = new ArrayList<>(quizCounts.entrySet());
//        quizCountList.sort(Map.Entry.comparingByValue());
//        Collections.reverse(quizCountList);
//        for (int i = 0; i < Math.min(5, quizCountList.size()); i++) {
//            Map.Entry<String, Integer> entry = quizCountList.get(i);
//            System.out.println((i + 1) + ". " + entry.getKey() + " (" + entry.getValue() + " quizzes)");
//        }
//
//        System.out.println("\nUsers with the best average score:");
//        List<Map.Entry<String, Double>> averageScoreList = new ArrayList<>(averageScores.entrySet());
//        averageScoreList.sort(Map.Entry.comparingByValue());
//        Collections.reverse(averageScoreList);
//        for (int i = 0; i < Math.min(5, averageScoreList.size()); i++) {
//            Map.Entry<String, Double> entry = averageScoreList.get(i);
//            System.out.println((i + 1) + ". " + entry.getKey() + " (" + entry.getValue() + "/10)");
//        }


//    private static JSONArray getQuestions() throws IOException, JSONException {
//        URL url = new URL("https://opentdb.com/api.php?amount=10");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = in.readLine()) != null) {
//                response.append(line);
//            }
//            in.close();
//            JSONObject jsonResponse = new JSONObject(response.toString());
//            return jsonResponse.getJSONArray("results");
//        } else {
//            System.out.println("");
//            return null;
//        }
//    }
//}