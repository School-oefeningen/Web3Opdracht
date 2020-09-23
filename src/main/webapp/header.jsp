<header>
    <h1>Lorenzo Catalano</h1>
    <nav>
        <ul>
            <li ${param.page eq 'Home'?'id="actual"':""}><a href="Controller?command=Home">Home</a></li>
            <li ${param.page eq 'People overview'?'id="actual"':""}><a href="Controller?command=Overview">Overview</a>
            </li>
            <li ${param.page eq 'Register'?'id="actual"':""}><a href="Controller?command=Register">Register</a></li>
        </ul>
    </nav>
    <h2>${param.page}</h2>
</header>