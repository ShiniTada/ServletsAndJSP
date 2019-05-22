package com.epam.couriers.tag;

class Paginator {

    /**
     * The name of the command to correctly form the request
     */
    private String commandName;

    /**
     * Limit of entities to display on pageNumber.
     */
    private int limit;

    /**
     * Total count entities in current table.
     */
    private int total = 0;

    /**
     * Current pageNumber number.
     */
    private int pageNumber = 1;

    /**
     * Describes count of links before and after
     * current pageNumber link.
     */
    private int countLinks = 3;

    /**
     * Class name of pagination list.
     */
    private String className = "pagination";

    /**
     * Disabled class name of link.
     */
    private String disabledClass = "disabled";

    /**
     * Active class name of link.
     */
    private String activeClass = "active";

    public Paginator(int limit, int total, String commandName) {
        this.limit = limit;
        this.total = total;
        this.commandName = commandName;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setCountLinks(int countLinks) {
        this.countLinks = countLinks;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDisabledClass(String disabledClass) {
        this.disabledClass = disabledClass;
    }

    public void setActiveClass(String activeClass) {
        this.activeClass = activeClass;
    }

    public String generate() {
        StringBuilder builder = new StringBuilder();

        final int lastPage = total / limit + ((total % limit > 0) ? 1 : 0);
        final int startLink = ((pageNumber - countLinks) > 0) ? pageNumber - countLinks : 1;
        final int endLink = ((pageNumber + countLinks) < lastPage) ? pageNumber + countLinks : lastPage;

        builder.append("<ul class=\"").append(className).append("\">");
        generatePreviousLink(builder);

        if (startLink > 1) {
            builder.append("<li><a href=\"?command=").append(commandName);
            builder.append("&pageNumber=1\">1</a></li>");
            builder.append("<li class=\"").append(disabledClass).append("\">");
            builder.append("<span>...</span></li>");
        }

        for (int i = startLink; i <= endLink; i++) {
            boolean isCurrentPage = this.pageNumber == i;
            builder.append("<li");

            if (isCurrentPage) {
                builder.append(" class=\"").append(activeClass).append("\"><a>");
            } else {
                builder.append("><a href=\"?command=").append(commandName);
                builder.append("&pageNumber=").append(i).append("\">");
            }

            builder.append(i).append("</a></li>");
        }

        if (endLink < lastPage) {
            builder.append("<li class=\"").append(disabledClass).append("\"><span>...</span></li>");
            builder.append("<li><a href=\"?command=").append(commandName);
            builder.append("&pageNumber=").append(lastPage).append("\">");
            builder.append(lastPage).append("</a></li>");
        }

        generateNextLink(builder, lastPage);
        builder.append("</ul>");

        return builder.toString();
    }

    /**
     * Generate link (previous or next).
     */
    private void generateLink(StringBuilder builder, int defaultPage, int wishedPage, int toPage) {
        boolean isThisCurrentPage = this.pageNumber == wishedPage;
        final int previousPage = isThisCurrentPage ? defaultPage : toPage;

        builder.append("<li");

        if (isThisCurrentPage) {
            builder.append(" class=\"").append(disabledClass);
            builder.append("\"><a>");
        } else {
            builder.append("><a href=\"?command=").append(commandName);
            builder.append("&pageNumber=").append(previousPage);
            builder.append("\">");
        }

        builder.append("&").append(wishedPage == 1 ? "l" : "r").append("aquo;</a></li>");
    }

    /**
     * Generate previous link.
     *
     * @param builder
     */
    private void generatePreviousLink(StringBuilder builder) {
        generateLink(builder, 1, 1, pageNumber - 1);
    }

    /**
     * Generate next link.
     *
     * @param builder
     * @param lastPage asdasdasd
     */
    private void generateNextLink(StringBuilder builder, int lastPage) {
        generateLink(builder, pageNumber, lastPage, pageNumber + 1);
    }

}
