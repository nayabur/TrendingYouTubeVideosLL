//https://stackoverflow.com/questions/19393202/how-can-i-add-a-space-in-between-two-outputs

import com.sun.org.apache.xerces.internal.dom.PSVIDOMImplementationImpl;

import java.util.Scanner;

public class Main {

    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("\t\tWELCOME TO THE YOUTUBE VIRAL VIDEO MAKER");

        printCategories();
        System.out.print("Select a category number: ");
        int category = keyboard.nextInt();

        long nano_StartTime = 0;
        long nano_EndTime = 0;
        long nano_TotalTime = 0;//nano_createEndTime-nano_createStartTime;

        String csvFile = "USvideosClean.csv";
        nano_StartTime = System.nanoTime();
        CSVParser csv = new CSVParser();
        VideoListOrdered pubDateList = csv.parse(csvFile, category); // use test instead
        VideoNode toFind = new VideoNode(category);
        nano_EndTime = System.nanoTime();

        while (pubDateList.getSize()==0){
            System.out.println("\t"+ "There are no videos in this category");
            System.out.print("Select a different category number: ");
            category = keyboard.nextInt();
            csv = new CSVParser();
            nano_StartTime = System.nanoTime();
            pubDateList = csv.parse(csvFile, category); // use test instead
            toFind = new VideoNode(category);
            nano_EndTime = System.nanoTime();
        }
        nano_TotalTime = nano_EndTime-nano_StartTime;

        nano_StartTime = System.nanoTime();
        System.out.print("Select a publication date between ");
        pubDateList.getLeftmost(pubDateList.header).videoContents.publishTime.print();
        System.out.print(" and ");
        pubDateList.getRightmost(pubDateList.header).videoContents.publishTime.print();
        System.out.println();
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        System.out.print("Enter date (mm/dd/yyyy): ");
        String date = keyboard.next();
        PublishDate selectedDate = new PublishDate();
        selectedDate.splitDate(date);
        toFind.videoContents.publishTime = selectedDate;
        nano_StartTime = System.nanoTime();
        VideoNode closest = pubDateList.getClosest(toFind);
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        // if the date entered and closest date are not exactly the same
        if (closest.videoContents.publishTime.compareTo(toFind.videoContents.publishTime) != 0) {
            closest.print(nano_TotalTime);
            System.exit(0);
        }
        // if this is the only video with that exact date
        if (closest.next == null || (closest.next.videoContents.publishTime.compareTo(toFind.videoContents.publishTime) != 0)) { //if only one exact match
            closest.print(nano_TotalTime);
            System.exit(0);
        }

        // comparing comment counts
        nano_StartTime = System.nanoTime();
        VideoListOrdered comCountList = new VideoListOrdered(9);

        while (closest.videoContents.publishTime.compareTo(toFind.videoContents.publishTime) == 0) {
            if (closest.next == null) {
                closest.resetRelations();
                break;
            }
            VideoNode leftOfClosest = closest.next;
            closest.resetRelations();
            comCountList.add(closest);
            closest = leftOfClosest;
        }

        System.out.print("Select a comment count between ");
        System.out.print(comCountList.getLeftmost(comCountList.header).videoContents.commentCount);
        System.out.print(" and ");
        System.out.print(comCountList.getRightmost(comCountList.header).videoContents.commentCount);
        System.out.println();
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        System.out.print("Enter amount: ");
        int comCount = keyboard.nextInt();
        toFind.videoContents.commentCount = comCount;
        nano_StartTime = System.nanoTime();
        VideoNode closestCom = comCountList.getClosest(toFind);
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        // if the comment count entered and closest comment count are not exactly the same
        if (closestCom.videoContents.compareTo(toFind.videoContents, 9) != 0) { //if not exact date match
            closestCom.print(nano_TotalTime);
            System.exit(0);
        }
        // if this is the only video with that exact comment count
        if (closestCom.next == null || (closestCom.next.videoContents.compareTo(toFind.videoContents, 9) != 0)) { //if only one exact match
            closestCom.print(nano_TotalTime);
            System.exit(0);
        }

        // comparing view counts

        nano_StartTime = System.nanoTime();
        VideoListOrdered viewsList = new VideoListOrdered(6);

        while (closestCom.videoContents.compareTo(toFind.videoContents, 9) == 0) {
            if (closestCom.next == null) {
                closestCom.resetRelations();
                viewsList.add(closestCom);
                break;
            }
            VideoNode leftOfClosest = closestCom.next;
            closestCom.resetRelations();
            viewsList.add(closestCom);
            closestCom = leftOfClosest;
        }

        System.out.print("Select a view count between ");
        System.out.print(viewsList.getLeftmost(viewsList.header).videoContents.views);
        System.out.print(" and ");
        System.out.print(viewsList.getRightmost(viewsList.header).videoContents.views);
        System.out.println();
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        System.out.print("Enter amount: ");
        int viewCount = keyboard.nextInt();
        toFind.videoContents.commentCount = comCount;
        nano_StartTime=System.nanoTime();
        VideoNode closestView = comCountList.getClosest(toFind);
        nano_EndTime = System.nanoTime();
        nano_TotalTime = nano_TotalTime + (nano_EndTime-nano_StartTime);
        // if the view count entered and closest view count are not exactly the same
        if (closestView.videoContents.compareTo(toFind.videoContents, 6) != 0) { //if not exact date match
            closestView.print(nano_TotalTime);
            System.exit(0);
        }
        // if this is the only video with that exact view count
        if (closestView.next == null || (closestView.next.videoContents.compareTo(toFind.videoContents, 6) != 0)) { //if only one exact match
            closestView.print(nano_TotalTime);
            System.exit(0);
        }
        // based off of the dataset, the next line should be unreachable
        System.out.println("MAKE ANOTHER TREE");
    }

    // prints standard youtube video categories and numbers
    public static void printCategories() {
        System.out.println("Video Categories: ");
        System.out.printf("\t%-30s %s\n", "1. Film & Animation", "2. Autos & Vehicles");
        System.out.printf("\t%-30s %s\n", "10. Music", "15. Pets & Animals");
        System.out.printf("\t%-30s %s\n",  "17. Sports", "18. Short Movies");
        System.out.printf("\t%-30s %s\n", "19. Travel & Events", "20. Gaming");
        System.out.printf("\t%-30s %s\n","21. Videoblogging","22. People & Blogs");
        System.out.printf("\t%-30s %s\n","23. Comedy","24. Entertainment");
        System.out.printf("\t%-30s %s\n", "25. News & Politics","26. Howto & Style");
        System.out.printf("\t%-30s %s\n", "27. Education","28. Science & Technology");
        System.out.printf("\t%-30s %s\n", "29. Nonprofits & Activism", "30. Movies");
        System.out.printf("\t%-30s %s\n","31. Anime/Animation","32. Action/Adventure");
        System.out.printf("\t%-30s %s\n","33. Classics","34. Comedy");
        System.out.printf("\t%-30s %s\n","35. Documentary","36. Drama");
        System.out.printf("\t%-30s %s\n","37. Family","38. Foreign");
        System.out.printf("\t%-30s %s\n","39. Horror","40. Sci-Fi/Fantasy");
        System.out.printf("\t%-30s %s\n","41. Thriller","42. Shorts");
        System.out.printf("\t%-30s %s\n","43. Shows","44. Trailers");
        System.out.println();
    }

}



