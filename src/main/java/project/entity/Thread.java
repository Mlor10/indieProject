package project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * A class to represent a thread
 */
@Entity(name = "Thread")
@Table(name = "thread")
public class Thread {
    @Column(name = "thread_title")
    private String threadTitle;
    @Column(name = "thread_content")
    private String threadContent;
    @Column(name = "thread_views")
    private int threadViews;
    @Column(name = "thread_date")
    private LocalDateTime threadDate;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "thread", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Reply> replies = new HashSet<>();

    /**
     * Instantiates a new thread
     */
    public Thread() {
    }

    /**
     * Constructor for a new thread
     * @param threadTitle thread title
     * @param threadContent thread content
     * @param threadViews thread views
     * @param user thread's user
     */
    public Thread(String threadTitle, String threadContent, int threadViews, LocalDateTime threadDate, User user) {
        this.threadTitle = threadTitle;
        this.threadContent = threadContent;
        this.threadViews = threadViews;
        this.threadDate = threadDate;
        this.user = user;
    }

    /**
     * Gets thread title
     *
     * @return the card name
     */
    public String getThreadTitle() {
        return threadTitle;
    }

    /**
     * Sets thread title
     *
     * @param threadTitle the card name
     */
    public void setThreadTitle(String threadTitle) {
        this.threadTitle = threadTitle;
    }

    /**
     * Gets thread content
     *
     * @return the thread content
     */
    public String getThreadContent() {
        return threadContent;
    }

    /**
     * Sets thread content
     *
     * @param threadContent thread content
     */
    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }

    /**
     * Gets thread views
     *
     * @return the thread views
     */
    public int getThreadViews() {
        return threadViews;
    }

    /**
     * Sets thread views
     *
     * @param threadViews thread views
     */
    public void setThreadViews(int threadViews) {
        this.threadViews = threadViews;
    }

    /**
     * Gets thread date
     *
     * @return thread date
     */
    public LocalDateTime getThreadDate() {
        return formatDateTime(this.threadDate);
    }

    /**
     * Sets the thread date
     *
     * @param threadDate thread date
     */
    public void setThreadDate(LocalDateTime threadDate) {
        this.threadDate = formatDateTime(threadDate);
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
     * Gets replies
     *
     * @return the replies
     */
    public Set<Reply> getReplies() {
        return replies;
    }

    /**
     * Sets replies
     *
     * @param replies the replies
     */
    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    /**
     * Add reply
     *
     * @param reply the reply
     */
    public void addReply(Reply reply) {
        replies.add(reply);
        reply.setThread(this);
    }

    /**
     * Remove reply
     *
     * @param reply the reply
     */
    public void removeReply(Reply reply) {
        replies.remove(reply);
        reply.setThread(null);
    }

    /**
     * Formats the localdatetime by removing anything less than seconds such as milliseconds
     * @param threadDate thread date to format
     * @return formatted thread date
     */
    public LocalDateTime formatDateTime(LocalDateTime threadDate) {
        return threadDate.truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Sorts list and returns it
     * @param replies thread replies
     * @return sorted replies by reply date
     */
    public List<Reply> sortReply(Set<Reply> replies) {
        List<Reply> replyList = new ArrayList<>(replies);
        Collections.sort(replyList);
        return replyList;
    }

    @Override
    public String toString() {
        return "Thread{" +
                "threadTitle='" + threadTitle + '\'' +
                ", threadContent='" + threadContent + '\'' +
                ", threadViews=" + threadViews +
                ", threadDate=" + threadDate +
                ", id=" + id +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thread thread = (Thread) o;
        return threadViews == thread.threadViews && id == thread.id && threadTitle.equals(thread.threadTitle) && threadContent.equals(thread.threadContent) && threadDate.equals(thread.threadDate) && user.equals(thread.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threadTitle, threadContent, threadViews, threadDate, id, user);
    }
}
