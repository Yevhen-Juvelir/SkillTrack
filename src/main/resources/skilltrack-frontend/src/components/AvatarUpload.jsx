import { useState, useRef, useEffect } from "react";
import axios from "axios";
import { Pencil } from "lucide-react";

function AvatarUpload() {
    const [avatarUrl, setAvatarUrl] = useState(localStorage.getItem("avatarUrl") || "");
    let fileInputRef = useRef(null);

    useEffect(() => {
        const fetchAvatarUrl = async () => {
            try {
                const response = await axios.get(
                    "api/users/avatar/getavatar?email=" + localStorage.getItem("userEmail"),
                    { responseType: 'text' }
                );
                console.log("api/users/avatar/getavatar?email=" + localStorage.getItem("userEmail"))
                console.log(response.data)
                console.log(localStorage.getItem("avatarUrl"))
                setAvatarUrl(response.data || localStorage.getItem("avatarUrl") || "")
            } catch (error) {
                console.log(error)
            }
        }

        fetchAvatarUrl();
    }, [])

    const cloudName = import.meta.env.VITE_CLOUDINARY_CLOUD_NAME;
    const uploadPreset = import.meta.env.VITE_CLOUDINARY_UPLOAD_PRESET;

    const uploadImage = async (file) => {
        if (!file) return;

        const formData = new FormData();
        formData.append("file", file);
        formData.append("upload_preset", uploadPreset);

        try {
            const res = await fetch(
                `https://api.cloudinary.com/v1_1/${cloudName}/image/upload`,
                {
                    method: "POST",
                    body: formData,
                }
            );

            const data = await res.json();

            if (data.secure_url) {
                localStorage.setItem("avatarUrl", data.secure_url);
                setAvatarUrl(data.secure_url);
                await axios.post('http://localhost:8080/api/users/avatar', {
                    email: localStorage.getItem("userEmail"),
                    avatarUrl: data.secure_url
                })
                    .then(function (response) {
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            } else {



                console.error("Upload failed:", data);
            }
        } catch (err) {
            console.error("Error uploading image:", err);
        }
    };

    return (
        <div className="flex items-center justify-center ui">
            <input
                type="file"
                accept="image/*"
                onChange={(e) => {
                    uploadImage(e.target.files[0])
                }}
                className="hidden"
                ref={fileInputRef}
            />

            <div className="relative">
                <img src={avatarUrl ? avatarUrl : "./icons/user.png"} alt="" className="mt-12 w-36 h-36" />
                <button
                    onClick={() => fileInputRef.current.click()}
                    className="absolute bottom-3 right-3 bg-white p-1 rounded-full shadow"
                >
                    <Pencil className="w-4 h-4 text-gray-600" />
                </button>
            </div>
        </div>


    );
}

export default AvatarUpload;