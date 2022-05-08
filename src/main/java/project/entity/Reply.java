package project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * A class to represent a card
 */
@Entity(name = "Reply")
@Table(name = "reply")
public class Reply implements Comparable<Reply>{
    @Column(name = "reply_title")
    private String replyTitle;
    @Column(name = "reply_content")
    private String replyContent;
    @Column(name = "reply_date")
    private LocalDateTime replyDate;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private Thread thread;

    @ManyToOne
    private User user;

    /**
     * Instantiates a new reply
     */
    public Reply() {
    }

    /**
     * Constructor for a new reply
     * @param replyTitle reply title
     * @param replyContent reply content
     * @param replyDate reply date
     * @param thread reply's thread
     * @param user reply's user
     */
    public Reply(String replyTitle, String replyContent, LocalDateTime replyDate, Thread thread, User user) {
        this.replyTitle = replyTitle;
        this.replyContent = replyContent;
        this.replyDate = replyDate;
        this.thread = thread;
        this.user = user;
    }

    /**
     * Gets reply title
     *
     * @return the card name
     */
    public String getReplyTitle() {
        return replyTitle;
    }

    /**
     * Sets reply title
     *
     * @param replyTitle the reply title
     */
    public void setReplyTitle(String replyTitle) {
        this.replyTitle = replyTitle;
    }

    /**
     * Gets reply content
     *
     * @return the reply content
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * Sets reply content
     *
     * @param replyContent reply content
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * Gets reply date
     *
     * @return the reply date
     */
    public LocalDateTime getReplyDate() {
        return formatDateTime(this.replyDate);
    }

    /**
     * Sets reply date
     *
     * @param replyDate the reply date
     */
    public void setReplyDate(LocalDateTime replyDate) {
        this.replyDate = formatDateTime(formatToCST(replyDate));
    }

    /**
     * Gets id
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets thread
     *
     * @return the thread
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Sets thread
     *
     * @param thread the thread
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     * Gets user
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Formats the localdatetime to CST timezone
     * @param replyDate reply date
     * @return reply date in CST timezone
     */
    public LocalDateTime formatToCST(LocalDateTime replyDate) {
        ZoneId timeZone = ZoneId.of("America/Chicago");
        return replyDate.atZone(timeZone).toLocalDateTime();
    }

    /**
     * Formats the localdatetime by removing anything less than seconds such as milliseconds
     * @param replyDate reply date to format
     * @return formatted reply date
     */
    public LocalDateTime formatDateTime(LocalDateTime replyDate) {
        return replyDate.truncatedTo(ChronoUnit.SECONDS);
    }

    @Override
    public String toString() {
        return "Reply{" +
                "replyTitle='" + replyTitle + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", replyDate=" + replyDate +
                ", id=" + id +
                ", thread=" + thread +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply = (Reply) o;
        return id == reply.id && replyTitle.equals(reply.replyTitle) && replyContent.equals(reply.replyContent) && replyDate.equals(reply.replyDate) && thread.equals(reply.thread) && user.equals(reply.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(replyTitle, replyContent, replyDate, id, thread, user);
    }

    @Override
    public int compareTo(Reply reply) {
        if (getReplyDate() == null || reply.getReplyDate() == null) {
            return 0;
        }
        return getReplyDate().compareTo(reply.getReplyDate());
    }
}
