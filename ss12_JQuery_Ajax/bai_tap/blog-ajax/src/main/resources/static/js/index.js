$(document).ready(function () {
    let page = 0;
    display(0);
    $('#btn-save').click(save);
    $('#btn-load-more').click(loadMore);
    function save() {
        let summary = $("#summary").val();
        let title = $("#title").val();
        let content = $("#contentB").val();
        let createdAt = $("#createdAt").val();
        let categoryId = $("#categorySelect").val();
        const blog = {
            summary,
            title,
            content,
            createdAt,
            category: {
                id: categoryId
            }
        }
        console.log(blog);
        $.ajax({
            url: `http://localhost:8080/api/v1/blogs`,
            dataType: "JSON",
            contentType: "application/json",
            type: "POST",
            data: JSON.stringify(blog),
            complete: function (data, textStatus, res) {
                console.log("Add success")
                console.log(res);
                page = 0;
                display(page);
            },
            error: function(res) {
                console.log("------Error-----")
                console.log(res)
            }
        });
    }
    function loadMore() {
        page++;
        display(page);
    }
    $('#searchForm').submit(function (event) {
        event.preventDefault();
        let keyword = $('#searchKeyword').val().trim();
        if (keyword === null) {
            display();
        } else {
            $.ajax({
                url: `http://localhost:8080/api/v1/blogs/search?keyword=${encodeURIComponent(keyword)}`,
                type: "GET",
                success: function (data) {
                    displayFromData(data);
                },
                error: function () {
                    $('#content').html('<tr><td colspan="7">No results found</td></tr>');
                }
            });
        }

    });
    display();
    displayCategories();

    function display(pageNumber) {
        let pageSize = 2;
        $.ajax({
            url: `http://localhost:8080/api/v1/blogs/page?page=${pageNumber}`,
            dataType: "json",
            type: "GET",
            success: function (data) {
                let content = "";
                for (let i = 0; i < data.content.length; i++) {
                    let stt = pageNumber * pageSize + i + 1;
                    content += `<tr>
                                    <td>${stt}</td>
                                    <td>${data.content[i].summary}</td>
                                    <td>${data.content[i].title}</td>
                                    <td>${data.content[i].content}</td>
                                    <td>${data.content[i].createdAt}</td>
                                    <td>${data.content[i].user ? data.content[i].user.username : 'Empty'}</td>
                                    <td>${data.content[i].category ? data.content[i].category.name : 'Empty'}</td>
                                </tr>`;
                }
                $("#content").append(content);

                // Ẩn nút nếu hết trang
                if (data.last || data.content.length === 0) {
                    isLastPage = true;
                    $('#btn-load-more').hide();
                }
            },
            error: function () {
                $('#content').html('<tr><td colspan="7">Error loading data</td></tr>');
            }
        });
    }

    function displayCategories() {
        $.ajax({
            url: `http://localhost:8080/api/v1/categories`,
            dataType: "JSON",
            type: "GET",
            success: function (data) {
                let options = '<option value="">-- All Categories --</option>';
                for (let i = 0; i < data.length; i++) {
                    options += `<option value="${data[i].id}">${data[i].name}</option>`;
                }
                $('#categorySelect').html(options);
            },
            error: function () {
                console.log("Error load categories");
            }
        })
    }
})
