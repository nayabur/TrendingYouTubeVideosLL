//https://www.javatpoint.com/java-string-compareto

// contains information on each video
public class Contents {
    public String videoID;
    public TrendDate trendingDate;
    public String title;
    public String channelTitle;
    public VideoCategory categoryID;
    public PublishDate publishTime;
    public int views;
    public int likes;
    public int dislikes;
    public int commentCount;
    public boolean commentsDisabled;

    public Contents(String[] line) {
        videoID = line[0];
        trendingDate = new TrendDate(line[1]);
        title = line[2];
        channelTitle = line[3];
        categoryID = new VideoCategory(Integer.parseInt(line[4]));
        publishTime = new PublishDate(line[5]);
        views = Integer.parseInt(line[6]);
        likes = Integer.parseInt(line[7]);
        dislikes = Integer.parseInt(line[8]);
        commentCount = Integer.parseInt(line[9]);
        commentsDisabled = Boolean.parseBoolean(line[10]);
    }

    public Contents(int cat) {
        videoID = null;
        trendingDate = null;
        title = null;
        channelTitle = null;
        categoryID = new VideoCategory(cat);
        publishTime = null;
        views = 0;
        likes = 0;
        dislikes = 0;
        commentCount = 0;
        commentsDisabled = false;
    }

    public int compareTo(Contents comparing, int index) {
        switch (index) {
            case 0:
                return this.videoID.compareTo(comparing.videoID);
            case 1:
                return this.trendingDate.compareTo(comparing.trendingDate);
            case 2:
                return this.title.compareTo(comparing.title);
            case 3:
                return this.channelTitle.compareTo(comparing.channelTitle);
            case 4:
                return compareToInts(this.categoryID.catID, comparing.categoryID.catID);
            case 5:
                return this.publishTime.compareTo(comparing.publishTime);
            case 6:
                return compareToInts(this.views, comparing.views);
            case 7:
                return compareToInts(this.likes, comparing.likes);
            case 8:
                return compareToInts(this.dislikes, comparing.dislikes);
            case 9:
                return compareToInts(this.commentCount, comparing.commentCount);
            case 10:
                return compareToBoolean(this.commentsDisabled, comparing.commentsDisabled);
            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }
    }

    private int compareToInts(int one, int two) {
        if (one > two) {
            return 1;
        } else if (one < two) {
            return -1;
        }
        return 0;
    }

    private int compareToBoolean(boolean one, boolean two) {
        if (one & two) {
            return 1;
        } else if (!one & !two) {
            return -1;
        }
        return 0;
    }

    public long subtract(Contents comparing, int index) {
        switch (index) {
            case 0:
                break;
            case 1:
                return this.trendingDate.subtract(comparing.trendingDate);
            case 2:
                break;
            case 3:
                break;
            case 4:
                return subtractInts(this.categoryID.catID, comparing.categoryID.catID);
            case 5:
                return this.publishTime.subtract(comparing.publishTime);
            case 6:
                return subtractInts(this.views, comparing.views);
            case 7:
                return subtractInts(this.likes, comparing.likes);
            case 8:
                return subtractInts(this.dislikes, comparing.dislikes);
            case 9:
                return subtractInts(this.commentCount, comparing.commentCount);
            case 10:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }
        return 0;
    }

    private long subtractInts(int one, int two) {
        return (long) Math.abs(one - two);
    }

    public void print(int index) {
        switch (index) {
            case 0:
                System.out.print(videoID);
                break;
            case 1:
                trendingDate.print();
                break;
            case 2:
                System.out.print(title);
                break;
            case 3:
                System.out.print(channelTitle);
                break;
            case 4:
                System.out.print(categoryID.getCategory());
                break;
            case 5:
                publishTime.print();
                break;
            case 6:
                System.out.print(views);
                break;
            case 7:
                System.out.print(likes);
                break;
            case 8:
                System.out.print(dislikes);
                break;
            case 9:
                System.out.print(commentCount);
                break;
            case 10:
                System.out.print(commentsDisabled);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }
    }

}
