$(document).ready(function () {
    let page = 0;
    display(0);
    displayCategories();

    $('#btn-save').click(save);
    $('#btn-load-more').click(loadMore);
    $('#btn-toggle-form-add').click(function () {
        $('#blog-form-add').toggleClass('d-none');
    });

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
                $("#content").html("");
                $('#btn-load-more').show();
                display(page);
                $('#blog-form-add').hide();
            },
            error: function (res) {
                console.log("------Error-----")
                console.log(res)
            }
        });
    }

    function loadMore() {
        page++;
        display(page);
    }

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
                                    <td>
                                        <button class="btn btn-warning btn-sm btn-edit" data-id="${data.content[i].id}">Edit</button>
                                        <button class="btn btn-danger btn-sm btn-delete" data-id="${data.content[i].id}">Delete</button>
                                    </td>
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

    $('#searchForm').submit(function (event) {
        event.preventDefault();
        let keyword = $('#searchKeyword').val().trim();
        if (keyword === "") {
            let page = 0;
            $("#content").html("");
            display(page);
            $('#btn-load-more').show();
        } else {
            $.ajax({
                url: `http://localhost:8080/api/v1/blogs/search?keyword=${encodeURIComponent(keyword)}`,
                type: "GET",
                success: function (data) {
                    displayData(data);
                },
                error: function () {
                    $('#content').html('<tr><td colspan="7">No results found</td></tr>');
                }
            });
        }
    });

    function displayData(data) {
        let content = "";
        for (let i = 0; i < data.length; i++) {
            content += `<tr>
                            <td>${i + 1}</td>
                            <td>${data[i].summary}</td>
                            <td>${data[i].title}</td>
                            <td>${data[i].content}</td>
                            <td>${data[i].createdAt}</td>
                            <td>${data[i].user ? data[i].user.username : 'Empty'}</td>
                            <td>${data[i].category ? data[i].category.name : 'Empty'}</td>
                            <td>
                                <button class="btn btn-warning btn-sm btn-edit" data-id="${data[i].id}">Edit</button>
                                <button class="btn btn-danger btn-sm btn-delete" data-id="${data[i].id}">Delete</button>
                            </td>
                        </tr>`
        }
        $('#content').html(content);
        $('#btn-load-more').hide();
    }

    $(document).on('click','.btn-delete', function() {
        const blogId = $(this).data('id');
        if (confirm("Are you sure?")) {
            $.ajax({
                url: `http://localhost:8080/api/v1/blogs/${blogId}`,
                type: "DELETE",
                success: function() {
                    console.log("Delete success")
                    $("#content").html("");
                    $("#btn-load-more").show();
                    page = 0;
                    display(page);
                },
                error: function() {
                    console.log("Delete fail")
                }
            })
        }
    })
})
